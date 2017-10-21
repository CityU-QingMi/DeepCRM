    public Result generateResponseHeaders(int request, int code, String reason, HttpFields fields, Callback callback)
    {
        request &= 0xFF_FF;

        final Charset utf8 = StandardCharsets.UTF_8;
        List<byte[]> bytes = new ArrayList<>(fields.size() * 2);
        int length = 0;

        if (code != 200 || sendStatus200)
        {
            // Special 'Status' header
            bytes.add(STATUS);
            length += STATUS.length + COLON.length;
            if (reason == null)
                reason = HttpStatus.getMessage(code);
            byte[] responseBytes = (code + " " + reason).getBytes(utf8);
            bytes.add(responseBytes);
            length += responseBytes.length + EOL.length;
        }

        // Other headers
        for (HttpField field : fields)
        {
            String name = field.getName();
            byte[] nameBytes = name.getBytes(utf8);
            bytes.add(nameBytes);

            String value = field.getValue();
            byte[] valueBytes = value.getBytes(utf8);
            bytes.add(valueBytes);

            length += nameBytes.length + COLON.length;
            length += valueBytes.length + EOL.length;
        }
        // End of headers
        length += EOL.length;

        final ByteBuffer buffer = byteBufferPool.acquire(length, true);
        BufferUtil.clearToFill(buffer);

        for (int i = 0; i < bytes.size(); i += 2)
            buffer.put(bytes.get(i)).put(COLON).put(bytes.get(i + 1)).put(EOL);
        buffer.put(EOL);

        BufferUtil.flipToFlush(buffer, 0);

        return generateContent(request, buffer, true, false, callback, FCGI.FrameType.STDOUT);
    }

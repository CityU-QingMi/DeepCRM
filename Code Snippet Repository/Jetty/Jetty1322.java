    private void generateTrailers(ByteBuffer buffer, HttpFields trailer)
    {
        // if we need CRLF add this to header
        if (_needCRLF)
            BufferUtil.putCRLF(buffer);

        // Add the chunk size to the header
        buffer.put(ZERO_CHUNK);

        int n=trailer.size();
        for (int f=0;f<n;f++)
        {
            HttpField field = trailer.getField(f);
            putTo(field,buffer);
        }

        BufferUtil.putCRLF(buffer);
    }

    public HttpResponseHeaderParseListener parse(ByteBuffer buf) throws ParseException
    {
        while (!isDone() && (buf.remaining() > 0))
        {
            String line = lineParser.parse(buf);
            if (line != null)
            {
                if (parseHeader(line))
                {
                    // Now finished with parsing the entire response header
                    // Save the remaining bytes for WebSocket to process.
                    
                    ByteBuffer copy = ByteBuffer.allocate(buf.remaining());
                    BufferUtil.put(buf,copy);
                    BufferUtil.flipToFlush(copy,0);
                    this.listener.setRemainingBuffer(copy);
                    return listener;
                }
            }
        }
        return null;
    }

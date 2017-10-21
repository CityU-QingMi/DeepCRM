    public void append(ByteBuffer buf)
    {
        try
        {
            while (buf.remaining() > 0)
            {
                appendByte(buf.get());
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

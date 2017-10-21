    public int read(ByteBuffer buf) throws IOException
    {
        int len = 0;
        while ((in.available() > 0) && (buf.remaining() > 0))
        {
            buf.put((byte)in.read());
            len++;
        }
        return len;
    }

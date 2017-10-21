    public void append(byte[] b, int offset, int length)
    {
        try
        {
            int end = offset + length;
            for (int i = offset; i < end; i++)
                appendByte(b[i]);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

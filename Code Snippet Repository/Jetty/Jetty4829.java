    public static void append(ByteBuffer to, byte[] b, int off, int len) throws BufferOverflowException
    {
        int pos = flipToFill(to);
        try
        {
            to.put(b, off, len);
        }
        finally
        {
            flipToFlush(to, pos);
        }
    }

    public static int append(ByteBuffer to, ByteBuffer b)
    {
        int pos = flipToFill(to);
        try
        {
            return put(b, to);
        }
        finally
        {
            flipToFlush(to, pos);
        }
    }

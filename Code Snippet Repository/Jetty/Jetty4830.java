    public static void append(ByteBuffer to, byte b)
    {
        int pos = flipToFill(to);
        try
        {
            to.put(b);
        }
        finally
        {
            flipToFlush(to, pos);
        }
    }

    public static int fill(ByteBuffer to, byte[] b, int off, int len)
    {
        int pos = flipToFill(to);
        try
        {
            int remaining = to.remaining();
            int take = remaining < len ? remaining : len;
            to.put(b, off, take);
            return take;
        }
        finally
        {
            flipToFlush(to, pos);
        }
    }

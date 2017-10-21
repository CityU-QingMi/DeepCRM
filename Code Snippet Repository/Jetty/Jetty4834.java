    public static void readFrom(InputStream is, int needed, ByteBuffer buffer) throws IOException
    {
        ByteBuffer tmp = allocate(8192);

        while (needed > 0 && buffer.hasRemaining())
        {
            int l = is.read(tmp.array(), 0, 8192);
            if (l < 0)
                break;
            tmp.position(0);
            tmp.limit(l);
            buffer.put(tmp);
        }
    }

    private static long perm6464(long c, long[][] p)
    {
        long out = 0L;
        for (int i = 8; --i >= 0;)
        {
            int t = (int) (0x00ff & c);
            c >>= 8;
            long tp = p[i << 1][t & 0x0f];
            out |= tp;
            tp = p[(i << 1) + 1][t >> 4];
            out |= tp;
        }
        return out;
    }

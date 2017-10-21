    private static long[] des_setkey(long keyword)
    {
        long K = perm6464(keyword, PC1ROT);
        long[] KS = new long[16];
        KS[0] = K & ~0x0303030300000000L;

        for (int i = 1; i < 16; i++)
        {
            KS[i] = K;
            K = perm6464(K, PC2ROT[Rotates[i] - 1]);

            KS[i] = K & ~0x0303030300000000L;
        }
        return KS;
    }

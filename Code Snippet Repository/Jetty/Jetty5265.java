    private static void init_perm(long[][] perm, byte[] p, int chars_out)
    {
        for (int k = 0; k < chars_out * 8; k++)
        {

            int l = p[k] - 1;
            if (l < 0) continue;
            int i = l >> 2;
            l = 1 << (l & 0x03);
            for (int j = 0; j < 16; j++)
            {
                int s = ((k & 0x07) + ((7 - (k >> 3)) << 3));
                if ((j & l) != 0x00) perm[i][j] |= (1L << s);
            }
        }
    }

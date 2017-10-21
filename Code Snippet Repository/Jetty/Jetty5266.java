    public static String crypt(String key, String setting)
    {
        long constdatablock = 0L; /* encryption constant */
        byte[] cryptresult = new byte[13]; /* encrypted result */
        long keyword = 0L;
        /* invalid parameters! */
        if (key == null || setting == null) return "*"; // will NOT match under
        // ANY circumstances!

        int keylen = key.length();

        for (int i = 0; i < 8; i++)
        {
            keyword = (keyword << 8) | ((i < keylen) ? 2 * key.charAt(i) : 0);
        }

        long[] KS = des_setkey(keyword);

        int salt = 0;
        for (int i = 2; --i >= 0;)
        {
            char c = (i < setting.length()) ? setting.charAt(i) : '.';
            cryptresult[i] = (byte) c;
            salt = (salt << 6) | (0x00ff & A64TOI[c]);
        }

        long rsltblock = des_cipher(constdatablock, salt, 25, KS);

        cryptresult[12] = ITOA64[(((int) rsltblock) << 2) & 0x3f];
        rsltblock >>= 4;
        for (int i = 12; --i >= 2;)
        {
            cryptresult[i] = ITOA64[((int) rsltblock) & 0x3f];
            rsltblock >>= 6;
        }

        return new String(cryptresult, 0, 13);
    }

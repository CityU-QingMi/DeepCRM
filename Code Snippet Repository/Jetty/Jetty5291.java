    public static int deduceKeyLength(String cipherSuite)
    {
        // Roughly ordered from most common to least common.
        if (cipherSuite == null)
            return 0;
        else if (cipherSuite.contains("WITH_AES_256_"))
            return 256;
        else if (cipherSuite.contains("WITH_RC4_128_"))
            return 128;
        else if (cipherSuite.contains("WITH_AES_128_"))
            return 128;
        else if (cipherSuite.contains("WITH_RC4_40_"))
            return 40;
        else if (cipherSuite.contains("WITH_3DES_EDE_CBC_"))
            return 168;
        else if (cipherSuite.contains("WITH_IDEA_CBC_"))
            return 128;
        else if (cipherSuite.contains("WITH_RC2_CBC_40_"))
            return 40;
        else if (cipherSuite.contains("WITH_DES40_CBC_"))
            return 40;
        else if (cipherSuite.contains("WITH_DES_CBC_"))
            return 56;
        else
            return 0;
    }

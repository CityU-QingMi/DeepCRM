    private static long toLong(byte[] payload)
    {
        long result = 0;
        for (int i = 0; i < 8; ++i)
        {
            result <<= 8;
            result |= (payload[i] & 0xFF);
        }
        return result;
    }

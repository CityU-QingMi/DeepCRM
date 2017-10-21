    private static byte[] toBytes(long value)
    {
        byte[] result = new byte[8];
        for (int i = result.length - 1; i >= 0; --i)
        {
            result[i] = (byte)(value & 0xFF);
            value >>= 8;
        }
        return result;
    }

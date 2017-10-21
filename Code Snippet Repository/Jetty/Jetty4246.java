    private byte[] prefixToBytes(int prefix, int length)
    {
        byte[] result = new byte[length];
        int index = 0;
        while (prefix / 8 > 0)
        {
            result[index] = -1;
            prefix -= 8;
            ++index;
        }

        if (index == result.length)
            return result;

        // Sets the _prefix_ most significant bits to 1
        result[index] = (byte)~((1 << (8 - prefix)) - 1);
        return result;
    }

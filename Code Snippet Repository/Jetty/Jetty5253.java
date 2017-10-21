    protected static boolean byteEquals(byte[] known, byte[] unknown)
    {
        if (known == unknown)
            return true;
        if (known == null || unknown == null)
            return false;
        boolean result = true;
        int l1 = known.length;
        int l2 = unknown.length;
        for (int i = 0; i < l2; ++i)
            result &= known[i%l1] == unknown[i];
        return result && l1 == l2;
    }

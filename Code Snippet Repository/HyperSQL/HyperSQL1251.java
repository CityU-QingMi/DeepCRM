    public boolean get(long key, long[] value) {

        int lookup = getLookup(key);

        if (lookup != -1) {
            value[0] = longValueTable[lookup];

            return true;
        }

        return false;
    }

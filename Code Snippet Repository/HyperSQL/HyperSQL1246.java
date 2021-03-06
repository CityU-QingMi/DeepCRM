    public int get(long key, int defaultValue) {

        int lookup = getLookup(key);

        if (lookup != -1) {
            return intValueTable[lookup];
        }

        return defaultValue;
    }

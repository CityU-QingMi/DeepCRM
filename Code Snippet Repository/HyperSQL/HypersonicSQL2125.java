    public long get(Object key, int defaultValue) {

        if (key == null) {
            throw new NoSuchElementException();
        }

        int hash   = key.hashCode();
        int lookup = getLookup(key, hash);

        if (lookup != -1) {
            return longValueTable[lookup];
        }

        return defaultValue;
    }

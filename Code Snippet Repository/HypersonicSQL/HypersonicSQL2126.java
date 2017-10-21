    public boolean get(Object key, long[] value) {

        if (key == null) {
            throw new NoSuchElementException();
        }

        int hash   = key.hashCode();
        int lookup = getLookup(key, hash);

        if (lookup != -1) {
            value[0] = longValueTable[lookup];

            return true;
        }

        return false;
    }

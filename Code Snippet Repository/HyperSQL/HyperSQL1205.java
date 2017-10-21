    public boolean get(Object key, int[] value) {

        if (key == null) {
            throw new NoSuchElementException();
        }

        int hash   = key.hashCode();
        int lookup = getLookup(key, hash);

        if (lookup != -1) {
            value[0] = intValueTable[lookup];

            return true;
        }

        return false;
    }

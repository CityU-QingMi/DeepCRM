    public Object get(Object key) {

        int hash   = key.hashCode();
        int lookup = getLookup(key, hash);

        if (lookup != -1) {
            return objectValueTable[lookup];
        }

        return null;
    }

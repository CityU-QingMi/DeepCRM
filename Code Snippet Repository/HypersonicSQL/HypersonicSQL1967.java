    public Object get(Object key) {

        int lookup = getLookup(key, key.hashCode());

        if (lookup < 0) {
            return null;
        } else {
            return objectKeyTable[lookup];
        }
    }

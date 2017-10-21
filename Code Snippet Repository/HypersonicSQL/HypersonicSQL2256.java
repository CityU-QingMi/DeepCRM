    protected boolean containsKey(Object key) {

        if (key == null) {
            return false;
        }

        if (hashIndex.elementCount == 0) {
            return false;
        }

        int lookup = getLookup(key, key.hashCode());

        return lookup == -1 ? false
                            : true;
    }

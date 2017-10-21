    protected boolean containsKey(int key) {

        if (hashIndex.elementCount == 0) {
            return false;
        }

        int lookup = getLookup(key);

        return lookup == -1 ? false
                            : true;
    }

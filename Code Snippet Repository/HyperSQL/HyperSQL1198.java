    public boolean get(int key, int[] value) {

        int lookup = getLookup(key);

        if (lookup != -1) {
            value[0] = intValueTable[lookup];

            return true;
        }

        return false;
    }

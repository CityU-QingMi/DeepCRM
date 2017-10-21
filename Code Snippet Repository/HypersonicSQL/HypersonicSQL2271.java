    protected int valueCount(Object key, int hash) {

        int lookup = getLookup(key, hash);

        if (lookup == -1) {
            return 0;
        }

        int count = 1;

        while (true) {
            lookup = BaseHashMap.this.hashIndex.getNextLookup(lookup);

            if (lookup == -1) {
                break;
            }

            if (BaseHashMap.this.objectKeyTable[lookup].equals(key)) {
                count++;
            }
        }

        return count;
    }

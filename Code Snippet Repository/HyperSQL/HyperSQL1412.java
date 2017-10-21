    protected int getObjectLookup(long key) {

        int  lookup = hashIndex.getLookup((int) key);
        long tempKey;

        for (; lookup >= 0; lookup = hashIndex.getNextLookup(lookup)) {
            tempKey = comparator.longKey(objectKeyTable[lookup]);

            if (tempKey == key) {
                break;
            }
        }

        return lookup;
    }

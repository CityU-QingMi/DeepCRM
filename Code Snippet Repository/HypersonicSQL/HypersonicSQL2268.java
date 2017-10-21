    protected int getLookup(long key) {

        int  lookup = hashIndex.getLookup((int) key);
        long tempKey;

        for (; lookup >= 0; lookup = hashIndex.getNextLookup(lookup)) {
            tempKey = longKeyTable[lookup];

            if (key == tempKey) {
                break;
            }
        }

        return lookup;
    }

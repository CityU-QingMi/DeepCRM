    protected int getLookup(int key) {

        int lookup = hashIndex.getLookup(key);
        int tempKey;

        for (; lookup >= 0; lookup = hashIndex.linkTable[lookup]) {
            tempKey = intKeyTable[lookup];

            if (key == tempKey) {
                break;
            }
        }

        return lookup;
    }

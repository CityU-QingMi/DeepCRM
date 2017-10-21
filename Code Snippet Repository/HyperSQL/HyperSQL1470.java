    protected Object getOrAddObject(Object key) {

        Object testValue;
        int    index      = hashIndex.getHashIndex(key.hashCode());
        int    lookup     = hashIndex.hashTable[index];
        int    lastLookup = -1;

        for (; lookup >= 0;
                lastLookup = lookup,
                lookup = hashIndex.getNextLookup(lookup)) {
            testValue = objectKeyTable[lookup];

            if (testValue.equals(key)) {
                if (accessCount > ACCESS_MAX) {
                    resetAccessCount();
                }

                accessTable[lookup] = accessCount++;

                return testValue;
            }
        }

        if (hashIndex.elementCount >= threshold) {
            reset();

            return getOrAddObject(key);
        }

        lookup                 = hashIndex.linkNode(index, lastLookup);
        objectKeyTable[lookup] = key;

        if (accessCount > ACCESS_MAX) {
            resetAccessCount();
        }

        accessTable[lookup] = accessCount++;

        return key;
    }

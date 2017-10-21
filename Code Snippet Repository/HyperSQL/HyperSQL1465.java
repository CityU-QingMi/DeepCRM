    protected String getOrAddString(Object key) {

        String testValue;
        int    index      = hashIndex.getHashIndex(key.hashCode());
        int    lookup     = hashIndex.hashTable[index];
        int    lastLookup = -1;

        for (; lookup >= 0;
                lastLookup = lookup,
                lookup = hashIndex.getNextLookup(lookup)) {
            testValue = (String) objectKeyTable[lookup];

            if (key.equals(testValue)) {
                if (accessCount > ACCESS_MAX) {
                    resetAccessCount();
                }

                accessTable[lookup] = accessCount++;

                return testValue;
            }
        }

        if (hashIndex.elementCount >= threshold) {
            reset();

            return getOrAddString(key);
        }

        testValue              = key.toString();
        lookup                 = hashIndex.linkNode(index, lastLookup);
        objectKeyTable[lookup] = testValue;

        if (accessCount > ACCESS_MAX) {
            resetAccessCount();
        }

        accessTable[lookup] = accessCount++;

        return testValue;
    }

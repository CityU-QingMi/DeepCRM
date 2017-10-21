    protected TimestampData getOrAddDate(long longKey) {

        TimestampData testValue;
        int           hash       = (int) longKey ^ (int) (longKey >>> 32);
        int           index      = hashIndex.getHashIndex(hash);
        int           lookup     = hashIndex.hashTable[index];
        int           lastLookup = -1;

        for (; lookup >= 0;
                lastLookup = lookup,
                lookup = hashIndex.getNextLookup(lookup)) {
            testValue = (TimestampData) objectKeyTable[lookup];

            if (testValue.getSeconds() == longKey) {
                if (accessCount > ACCESS_MAX) {
                    resetAccessCount();
                }

                accessTable[lookup] = accessCount++;

                return testValue;
            }
        }

        if (hashIndex.elementCount >= threshold) {
            reset();

            return getOrAddDate(longKey);
        }

        lookup                 = hashIndex.linkNode(index, lastLookup);
        testValue              = new TimestampData(longKey);
        objectKeyTable[lookup] = testValue;

        if (accessCount > ACCESS_MAX) {
            resetAccessCount();
        }

        accessTable[lookup] = accessCount++;

        return testValue;
    }

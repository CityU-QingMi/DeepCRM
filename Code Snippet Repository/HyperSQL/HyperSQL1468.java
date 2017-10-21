    protected Double getOrAddDouble(long longKey) {

        Double testValue;
        int index = hashIndex.getHashIndex((int) (longKey ^ (longKey >>> 32)));
        int    lookup     = hashIndex.hashTable[index];
        int    lastLookup = -1;

        for (; lookup >= 0;
                lastLookup = lookup,
                lookup = hashIndex.getNextLookup(lookup)) {
            testValue = (Double) objectKeyTable[lookup];

            if (Double.doubleToLongBits(testValue.doubleValue()) == longKey) {
                if (accessCount > ACCESS_MAX) {
                    resetAccessCount();
                }

                accessTable[lookup] = accessCount++;

                return testValue;
            }
        }

        if (hashIndex.elementCount >= threshold) {
            reset();

            return getOrAddDouble(longKey);
        }

        lookup                 = hashIndex.linkNode(index, lastLookup);
        testValue              = new Double(Double.longBitsToDouble(longKey));
        objectKeyTable[lookup] = testValue;

        if (accessCount > ACCESS_MAX) {
            resetAccessCount();
        }

        accessTable[lookup] = accessCount++;

        return testValue;
    }

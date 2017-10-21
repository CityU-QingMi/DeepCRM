    protected Integer getOrAddInteger(int intKey) {

        Integer testValue;
        int     index      = hashIndex.getHashIndex(intKey);
        int     lookup     = hashIndex.hashTable[index];
        int     lastLookup = -1;

        for (; lookup >= 0;
                lastLookup = lookup,
                lookup = hashIndex.getNextLookup(lookup)) {
            testValue = (Integer) objectKeyTable[lookup];

            int keyValue = testValue.intValue();

            if (keyValue == intKey) {
                if (accessCount > ACCESS_MAX) {
                    resetAccessCount();
                }

                accessTable[lookup] = accessCount++;

                hits++;

                return testValue;
            } else if (keyValue > intKey) {
                break;
            }
        }

        if (hashIndex.elementCount >= threshold) {
            reset();

            return getOrAddInteger(intKey);
        }

        lookup                 = hashIndex.linkNode(index, lastLookup);
        testValue              = Integer.valueOf(intKey);
        objectKeyTable[lookup] = testValue;

        if (accessCount > ACCESS_MAX) {
            resetAccessCount();
        }

        accessTable[lookup] = accessCount++;

        return testValue;
    }

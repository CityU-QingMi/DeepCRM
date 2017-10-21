    protected Object removeObject(Object objectKey, boolean removeRow) {

        if (objectKey == null) {
            return null;
        }

        int    hash        = objectKey.hashCode();
        int    index       = hashIndex.getHashIndex(hash);
        int    lookup      = hashIndex.hashTable[index];
        int    lastLookup  = -1;
        Object returnValue = null;

        for (; lookup >= 0;
                lastLookup = lookup,
                lookup = hashIndex.getNextLookup(lookup)) {
            if (objectKeyTable[lookup].equals(objectKey)) {
                returnValue            = objectKeyTable[lookup];
                objectKeyTable[lookup] = null;

                hashIndex.unlinkNode(index, lastLookup, lookup);

                if (isObjectValue) {
                    returnValue              = objectValueTable[lookup];
                    objectValueTable[lookup] = null;
                }

                if (removeRow) {
                    removeRow(lookup);
                }

                return returnValue;
            }
        }

        // not found
        return returnValue;
    }

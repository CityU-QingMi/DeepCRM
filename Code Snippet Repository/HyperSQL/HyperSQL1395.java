    public void clear() {

        if (hashIndex.modified) {
            accessCount  = 0;
            accessMin    = accessCount;
            hasZeroKey   = false;
            zeroKeyIndex = -1;

            clearElementArrays(0, hashIndex.linkTable.length);
            hashIndex.clear();

            if (minimizeOnEmpty) {
                rehash(initialCapacity);
            }
        }
    }

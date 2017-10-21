    public int linkNode(int index, final int lastLookup) {

        // get the first reclaimed slot
        int lookup = reclaimedNodePointer;

        if (lookup == -1) {
            lookup = newNodePointer++;
        } else {

            // reset the first reclaimed slot
            reclaimedNodePointer = linkTable[lookup];
        }

        // link the node
        int nextLookup;

        if (lastLookup == -1) {
            nextLookup       = hashTable[index];
            hashTable[index] = lookup;
        } else {
            nextLookup            = linkTable[lastLookup];
            linkTable[lastLookup] = lookup;
        }

        linkTable[lookup] = nextLookup;

        elementCount++;

        modified = true;

        return lookup;
    }

    public void unlinkNode(int index, int lastLookup, int lookup) {

        // unlink the node
        if (lastLookup == -1) {
            hashTable[index] = linkTable[lookup];
        } else {
            linkTable[lastLookup] = linkTable[lookup];
        }

        // add to reclaimed list
        linkTable[lookup]    = reclaimedNodePointer;
        reclaimedNodePointer = lookup;

        elementCount--;

        if (elementCount == 0) {
            Arrays.fill(linkTable, 0, newNodePointer, 0);
            resetTables();
        }
    }

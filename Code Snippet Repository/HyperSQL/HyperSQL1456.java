    public boolean removeEmptyNode(int lookup) {

        boolean found      = false;
        int     lastLookup = -1;

        for (int i = reclaimedNodePointer; i >= 0;
                lastLookup = i, i = linkTable[i]) {
            if (i == lookup) {
                if (lastLookup == -1) {
                    reclaimedNodePointer = linkTable[lookup];
                } else {
                    linkTable[lastLookup] = linkTable[lookup];
                }

                found = true;

                break;
            }
        }

        if (!found) {
            return false;
        }

        for (int i = 0; i < newNodePointer; i++) {
            if (linkTable[i] > lookup) {
                linkTable[i]--;
            }
        }

        System.arraycopy(linkTable, lookup + 1, linkTable, lookup,
                         newNodePointer - lookup - 1);

        linkTable[newNodePointer - 1] = 0;

        newNodePointer--;

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] > lookup) {
                hashTable[i]--;
            }
        }

        return true;
    }

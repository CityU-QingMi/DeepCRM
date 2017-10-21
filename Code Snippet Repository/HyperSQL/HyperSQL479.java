    boolean isFromLeft(PersistentStore store) {

        NodeAVLDiskLarge node = findNode(store);

        if (node.iParent == NO_POS) {
            return true;
        }

        NodeAVLDiskLarge temp = findNode(store, iParent);

        return row.getPos() == temp.iLeft;
    }

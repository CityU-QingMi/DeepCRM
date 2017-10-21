    boolean isFromLeft(PersistentStore store) {

        NodeAVLDisk node = findNode(store);

        if (node.iParent == NO_POS) {
            return true;
        }

        NodeAVLDisk temp = findNode(store, node.iParent);

        return row.getPos() == temp.iLeft;
    }

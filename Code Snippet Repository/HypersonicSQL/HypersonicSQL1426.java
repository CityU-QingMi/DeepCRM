    NodeAVL getLeft(PersistentStore store) {

        NodeAVLDiskLarge node = findNode(store);

        if (node.iLeft == NO_POS) {
            return null;
        }

        return findNode(store, node.iLeft);
    }

    NodeAVL getParent(PersistentStore store) {

        NodeAVLDiskLarge node = findNode(store);

        if (node.iParent == NO_POS) {
            return null;
        }

        return findNode(store, iParent);
    }

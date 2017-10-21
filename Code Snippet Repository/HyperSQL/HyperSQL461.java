    NodeAVL getParent(PersistentStore store) {

        NodeAVLDisk node = findNode(store);

        if (node.iParent == NO_POS) {
            return null;
        }

        return findNode(store, node.iParent);
    }

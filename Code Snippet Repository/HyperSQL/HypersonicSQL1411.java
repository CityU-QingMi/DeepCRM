    NodeAVL getRight(PersistentStore store) {

        NodeAVLDisk node = findNode(store);

        if (node.iRight == NO_POS) {
            return null;
        }

        return findNode(store, node.iRight);
    }

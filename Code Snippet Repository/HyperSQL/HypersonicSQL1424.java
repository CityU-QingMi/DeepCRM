    boolean isLeft(PersistentStore store, NodeAVL n) {

        NodeAVLDiskLarge node = findNode(store);

        if (n == null) {
            return node.iLeft == NO_POS;
        }

        return node.iLeft == n.getPos();
    }

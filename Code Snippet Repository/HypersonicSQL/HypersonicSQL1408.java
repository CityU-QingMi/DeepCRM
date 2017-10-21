    boolean isLeft(PersistentStore store, NodeAVL n) {

        NodeAVLDisk node = findNode(store);

        if (n == null) {
            return node.iLeft == NO_POS;
        }

        return node.iLeft == n.getPos();
    }

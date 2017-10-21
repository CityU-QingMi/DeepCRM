    boolean isRight(PersistentStore store, NodeAVL n) {

        NodeAVLDiskLarge node = findNode(store);

        if (n == null) {
            return node.iRight == NO_POS;
        }

        return node.iRight == n.getPos();
    }

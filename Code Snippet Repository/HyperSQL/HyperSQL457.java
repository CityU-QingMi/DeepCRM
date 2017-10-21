    boolean isRight(PersistentStore store, NodeAVL n) {

        NodeAVLDisk node = findNode(store);

        if (n == null) {
            return node.iRight == NO_POS;
        }

        return node.iRight == n.getPos();
    }

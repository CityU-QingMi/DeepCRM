    NodeAVL setRight(PersistentStore store, NodeAVL n) {

        RowAVLDisk  row  = (RowAVLDisk) store.get(this.row, true);
        NodeAVLDisk node = (NodeAVLDisk) row.getNode(iId);

        node.iRight = n == null ? NO_POS
                                : (int) n.getPos();

        row.setNodesChanged();

        row.keepInMemory(false);

        return node;
    }

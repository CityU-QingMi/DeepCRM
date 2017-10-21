    NodeAVL setRight(PersistentStore store, NodeAVL n) {

        RowAVLDisk       row  = (RowAVLDisk) store.get(this.row, true);
        NodeAVLDiskLarge node = (NodeAVLDiskLarge) row.getNode(iId);

        node.iRight = n == null ? NO_POS
                                : n.getPos();

        row.setNodesChanged();
        row.keepInMemory(false);

        return node;
    }

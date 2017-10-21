    NodeAVL setParent(PersistentStore store, NodeAVL n) {

        RowAVLDisk       row  = (RowAVLDisk) store.get(this.row, true);
        NodeAVLDiskLarge node = (NodeAVLDiskLarge) row.getNode(iId);

        row.setNodesChanged();

        node.iParent = n == null ? NO_POS
                                 : n.getPos();

        row.keepInMemory(false);

        return node;
    }

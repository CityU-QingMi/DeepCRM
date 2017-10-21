    NodeAVL setParent(PersistentStore store, NodeAVL n) {

        RowAVLDisk  row  = (RowAVLDisk) store.get(this.row, true);
        NodeAVLDisk node = (NodeAVLDisk) row.getNode(iId);

        row.setNodesChanged();

        node.iParent = n == null ? NO_POS
                                 : (int) n.getPos();

        row.keepInMemory(false);

        return node;
    }

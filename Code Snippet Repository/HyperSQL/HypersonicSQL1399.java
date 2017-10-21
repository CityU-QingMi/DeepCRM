    public NodeAVL setBalance(PersistentStore store, int b) {

        RowAVLDisk  row  = (RowAVLDisk) store.get(this.row, true);
        NodeAVLDisk node = (NodeAVLDisk) row.getNode(iId);

        row.setNodesChanged();

        node.iBalance = b;

        row.keepInMemory(false);

        return node;
    }

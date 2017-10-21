    public RowAVLDiskLarge(PersistentStore store, RowInputInterface in) throws IOException {

        super(store.getTable());

        position    = in.getFilePosition();
        storageSize = in.getSize();

        int indexcount = store.getAccessorKeys().length;

        nPrimaryNode = new NodeAVLDiskLarge(this, in, 0);

        NodeAVL n = nPrimaryNode;

        for (int i = 1; i < indexcount; i++) {
            n.nNext = new NodeAVLDiskLarge(this, in, i);
            n       = n.nNext;
        }

        rowData = in.readData(table.getColumnTypes());
    }

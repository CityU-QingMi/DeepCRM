    public RowAVLDisk(PersistentStore store,
                      RowInputInterface in) throws IOException {

        super(store.getTable(), (Object[]) null);

        position    = in.getFilePosition();
        storageSize = in.getSize();

        int indexcount = store.getAccessorKeys().length;

        nPrimaryNode = new NodeAVLDisk(this, in, 0);

        NodeAVL n = nPrimaryNode;

        for (int i = 1; i < indexcount; i++) {
            n.nNext = new NodeAVLDisk(this, in, i);
            n       = n.nNext;
        }

        rowData = in.readData(table.getColumnTypes());
    }

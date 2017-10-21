    public RowAVLDiskData(RowStoreAVLDiskData store, TableBase t,
                          RowInputInterface in) throws IOException {

        super(t, (Object[]) null);

        setNewNodes(store);

        position       = in.getFilePosition();
        storageSize    = in.getSize();
        rowData        = in.readData(table.getColumnTypes());
        hasDataChanged = false;
        this.store     = store;
    }

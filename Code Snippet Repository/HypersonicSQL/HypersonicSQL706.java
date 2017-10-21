    public RowDiskDataChange(Session session, PersistentStore store,
                             RowInputInterface in) throws IOException {

        super(store, in);

        targetTable = store.getTable().database.schemaManager.findTable(session,
                (String) rowData[COL_POS_TABLE_NAME],
                (String) rowData[COL_POS_SCHEMA_NAME], null);

        if ((Boolean) rowData[COL_POS_IS_UPDATE]) {
            updateData = in.readData(targetTable.colTypes);

            RowInputBinary bin = (RowInputBinary) in;

            if (bin.readNull()) {
                updateColMap = null;
            } else {
                updateColMap = bin.readIntArray();
            }
        } else {
            updateData   = null;
            updateColMap = null;
        }
    }

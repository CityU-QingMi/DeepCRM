    private void readDataIntoTable(Session session, PersistentStore store,
                                   TextFileReader reader) {
        while (true) {
            RowInputInterface rowIn = reader.readObject();

            if (rowIn == null) {
                break;
            }

            Row row = (Row) store.get(rowIn);

            if (row == null) {
                break;
            }

            Object[] data = row.getData();

            systemUpdateIdentityValue(data);
            enforceRowConstraints(session, data);
            store.indexRow(session, row);
        }
    }

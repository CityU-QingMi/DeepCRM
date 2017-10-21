    public final void setStore(Session session, Table table,
                               PersistentStore store) {

        long dbscts = database.schemaManager.getSchemaChangeTimestamp();

        if (store.getTimestamp() == dbscts
                && !isNonCachedTable(table.getName().name)) {
            return;
        }

        // fredt - clear the contents of table and generate
        store.removeAll();
        store.setTimestamp(dbscts);

        int tableIndex = getSysTableID(table.getName().name);

        generateTable(session, store, tableIndex);
    }

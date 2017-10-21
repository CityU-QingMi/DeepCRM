    void insertValuesIntoSubqueryTable(Session session,
                                       PersistentStore store) {

        for (int i = 0; i < nodes.length; i++) {
            Object[] values = nodes[i].getRowValue(session);
            Object[] data   = store.getTable().getEmptyRowData();

            for (int j = 0; j < nodeDataTypes.length; j++) {
                data[j] = nodeDataTypes[j].convertToType(session, values[j],
                        nodes[i].nodes[j].dataType);
            }

            Row row = (Row) store.getNewCachedObject(session, data, false);

            try {
                store.indexRow(session, row);
            } catch (HsqlException e) {}
        }
    }

    public RowAction addDeleteAction(Session session, Table table,
                                     PersistentStore store, Row row,
                                     int[] colMap) {

        RowAction action;

        synchronized (row) {
            action = RowAction.addDeleteAction(session, table, row, colMap);
        }

        session.rowActionList.add(action);
        store.delete(session, row);

        row.rowAction = null;

        return action;
    }

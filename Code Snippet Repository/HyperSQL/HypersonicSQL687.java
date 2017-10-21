    public static RowAction addInsertAction(Session session, TableBase table,
            Row row) {

        RowAction action = new RowAction(session, table, ACTION_INSERT, row,
                                         null);

        row.rowAction = action;

        return action;
    }

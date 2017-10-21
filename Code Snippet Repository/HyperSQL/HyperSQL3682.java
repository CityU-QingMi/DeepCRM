    public static RowAction addDeleteAction(Session session, TableBase table,
            Row row, int[] colMap) {

        RowAction action = row.rowAction;

        if (action == null) {
            action = new RowAction(session, table, ACTION_DELETE, row, colMap);
            row.rowAction = action;

            return action;
        }

        return action.addDeleteAction(session, colMap);
    }

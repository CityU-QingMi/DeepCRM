    public static boolean addRefAction(Session session, Row row,
                                       int[] colMap) {

        RowAction action = row.rowAction;

        if (action == null) {
            action = new RowAction(session, row.getTable(), ACTION_REF, row,
                                   colMap);
            row.rowAction = action;

            return true;
        }

        return action.addRefAction(session, colMap);
    }

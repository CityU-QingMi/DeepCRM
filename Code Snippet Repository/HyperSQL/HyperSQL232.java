    public boolean canRead(Session session, PersistentStore store, Row row,
                           int mode, int[] colMap) {

        RowAction action = row.rowAction;

        if (action == null) {
            return true;
        }

        if (action.table.tableType == TableBase.TEMP_TABLE) {
            return true;
        }

        return action.canRead(session, TransactionManager.ACTION_READ);
    }

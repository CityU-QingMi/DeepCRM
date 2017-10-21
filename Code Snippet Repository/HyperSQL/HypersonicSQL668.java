    boolean isDeleted(Session session, PersistentStore store) {

        RowAction action;
        Row       row = (Row) store.get(this, false);

        if (row == null) {
            return true;
        }

        action = row.rowAction;

        if (action == null) {
            return false;
        }

        return !action.canRead(session, TransactionManager.ACTION_READ);
    }

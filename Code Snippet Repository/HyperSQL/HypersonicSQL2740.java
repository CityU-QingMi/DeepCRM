    public void postCommitAction(Session session, RowAction action) {

        if (action.getType() == RowAction.ACTION_NONE) {
            database.txManager.removeTransactionInfo(action.getPos());
        } else if (action.getType() == RowAction.ACTION_DELETE_FINAL
                   && !action.isDeleteComplete()) {
            action.setDeleteComplete();

            Row row = action.getRow();

            if (row == null) {
                row = (Row) get(action.getPos(), false);
            }

            delete(session, row);

            // remove info after delete but before removing persistence
            database.txManager.removeTransactionInfo(row);
            remove(row);
        }
    }

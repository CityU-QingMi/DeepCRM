    public void rollbackRow(Session session, Row row, int changeAction,
                            int txModel) {

        switch (changeAction) {

            case RowAction.ACTION_DELETE :
                if (txModel == TransactionManager.LOCKS) {
                    row = (Row) get(row, true);

                    ((RowAVL) row).setNewNodes(this);
                    row.keepInMemory(false);
                    indexRow(session, row);
                }
                break;

            case RowAction.ACTION_INSERT :
                delete(session, row);

                // remove info after delete but before removing persistence
                database.txManager.removeTransactionInfo(row);
                remove(row);
                break;

            case RowAction.ACTION_INSERT_DELETE :
                if (txModel == TransactionManager.LOCKS) {
                    remove(row);
                } else {

                    // INSERT + DELETE
                    delete(session, row);

                    // remove info after delete but before removing persistence
                    database.txManager.removeTransactionInfo(row);
                    remove(row);
                }
                break;
        }
    }

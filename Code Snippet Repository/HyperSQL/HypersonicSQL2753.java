    public void rollbackRow(Session session, Row row, int changeAction,
                            int txModel) {

        switch (changeAction) {

            case RowAction.ACTION_DELETE :
                row = (Row) get(row, true);

                ((RowAVL) row).setNewNodes(this);
                row.keepInMemory(false);
                indexRow(session, row);
                break;

            case RowAction.ACTION_INSERT :
                delete(session, row);
                remove(row);
                break;

            case RowAction.ACTION_INSERT_DELETE :

                // INSERT + DELEETE
                remove(row);
                break;
        }
    }

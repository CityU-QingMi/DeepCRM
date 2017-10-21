    public void commitRow(Session session, Row row, int changeAction,
                          int txModel) {

        switch (changeAction) {

            case RowAction.ACTION_DELETE :
                remove(row);
                break;

            case RowAction.ACTION_INSERT :
                break;

            case RowAction.ACTION_INSERT_DELETE :

                // INSERT + DELEETE
                remove(row);
                break;

            case RowAction.ACTION_DELETE_FINAL :
                throw Error.runtimeError(ErrorCode.U_S0500, "RowStore");
        }
    }

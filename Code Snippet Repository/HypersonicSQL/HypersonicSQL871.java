    public void addDeleteAction(Table table, PersistentStore store, Row row,
                                int[] colMap) {

//        tempActionHistory.add("add delete action " + actionTimestamp);
        if (abortTransaction) {
            throw Error.error(ErrorCode.X_40001);
        }

        if (abortAction) {
            throw Error.error(ErrorCode.X_40502);
        }

        database.txManager.addDeleteAction(this, table, store, row, colMap);
    }

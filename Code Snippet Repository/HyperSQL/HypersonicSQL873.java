    void addInsertAction(Table table, PersistentStore store, Row row,
                         int[] changedColumns) {

//        tempActionHistory.add("add insert to transaction " + actionTimestamp);
        database.txManager.addInsertAction(this, table, store, row,
                                           changedColumns);

        // abort only after adding so that the new row gets removed from indexes
        if (abortTransaction) {
            throw Error.error(ErrorCode.X_40001);
        }

        if (abortAction) {
            throw Error.error(ErrorCode.X_40502);
        }
    }

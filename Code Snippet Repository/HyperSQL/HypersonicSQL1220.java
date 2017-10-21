    public void addTransactionInfo(CachedObject object) {

        if (object.isMemory()) {
            return;
        }

        Row row = (Row) object;

        if (row.getTable().tableType == TableBase.CACHED_TABLE) {
            RowAction action = (RowAction) rowActionMap.get(object.getPos());

            if (action != null) {
                HsqlException e = Error.error(ErrorCode.X_40501,
                                              "TXManager - row exists");

                this.database.logger.logSevereEvent("TXManager MVROWS", e);

                throw e;
            }

            rowActionMap.put(object.getPos(), row.rowAction);
        }
    }

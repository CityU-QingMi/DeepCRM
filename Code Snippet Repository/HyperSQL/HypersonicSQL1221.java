    public void setTransactionInfo(PersistentStore store,
                                   CachedObject object) {

        if (object.isMemory()) {
            return;
        }

        Row row = (Row) object;

        if (row.getTable().tableType == TableBase.CACHED_TABLE) {
            RowAction rowact = (RowAction) rowActionMap.get(row.getPos());

            row.rowAction = rowact;
        }
    }

    public void addTransactionInfo(CachedObject object) {

        if (object.isMemory()) {
            return;
        }

        Row row = (Row) object;

        if (row.getTable().tableType == TableBase.CACHED_TABLE) {
            rowActionMap.put(object.getPos(), row.rowAction);
        }
    }

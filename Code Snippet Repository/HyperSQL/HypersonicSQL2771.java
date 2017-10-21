    public CachedObject getNewCachedObject(Session session, Object object,
                                           boolean tx) {

        int id  = rowIdSequence.getAndIncrement();
        Row row = new RowAVL(table, (Object[]) object, id, this);

        if (tx) {
            RowAction.addInsertAction(session, table, row);
        }

        return row;
    }

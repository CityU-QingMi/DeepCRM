    public CachedObject getNewCachedObject(Session session, Object object,
                                           boolean tx) {

        if (!isCached) {
            if (useDisk && elementCount.get() >= maxMemoryRowCount) {
                changeToDiskTable(session);
            }
        }

        Row row;

        if (isCached) {
            row = new RowAVLDisk(table, (Object[]) object, this);
        } else {
            int id = rowIdSequence++;

            row = new RowAVL(table, (Object[]) object, id, this);
        }

        add(session, row, tx);

        return row;
    }

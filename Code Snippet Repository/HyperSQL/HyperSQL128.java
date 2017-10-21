    public RowIterator rowIteratorClustered(Session session) {

        PersistentStore store = getRowStore(session);
        Index           index = getClusteredIndex();

        if (index == null) {
            index = getPrimaryIndex();
        }

        return index.firstRow(session, store, 0, null);
    }

    public RowIterator findFirstRow(Session session, PersistentStore store,
                                    Object[] rowdata, int matchCount,
                                    int distinctCount, int compareType,
                                    boolean reversed, boolean[] map) {

        NodeAVL node = findNode(session, store, rowdata, defaultColMap,
                                matchCount, compareType,
                                TransactionManager.ACTION_READ, reversed);

        if (node == null) {
            return emptyIterator;
        }

        return new IndexRowIterator(session, store, this, node, distinctCount,
                                    false, reversed);
    }

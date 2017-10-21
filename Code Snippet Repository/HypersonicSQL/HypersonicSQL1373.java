    public RowIterator findFirstRowNotNull(Session session,
                                           PersistentStore store) {

        NodeAVL node = findNode(session, store, nullData, this.defaultColMap,
                                1, OpTypes.NOT,
                                TransactionManager.ACTION_READ, false);

        if (node == null) {
            return emptyIterator;
        }

        return new IndexRowIterator(session, store, this, node, 0, false,
                                    false);
    }

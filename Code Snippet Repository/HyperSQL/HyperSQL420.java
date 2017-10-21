    public RowIterator lastRow(Session session, PersistentStore store,
                               int distinctCount, boolean[] map) {

        store.readLock();

        try {
            NodeAVL x = getAccessor(store);
            NodeAVL l = x;

            while (l != null) {
                x = l;
                l = x.getRight(store);
            }

            while (session != null && x != null) {
                Row row = x.getRow(store);

                if (session.database.txManager.canRead(
                        session, store, row, TransactionManager.ACTION_READ,
                        null)) {
                    break;
                }

                x = last(store, x);
            }

            if (x == null) {
                return emptyIterator;
            }

            return new IndexRowIterator(session, store, this, x,
                                        distinctCount, false, true);
        } finally {
            store.readUnlock();
        }
    }

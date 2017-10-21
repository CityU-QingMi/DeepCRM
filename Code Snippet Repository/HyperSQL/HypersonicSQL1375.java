    public RowIterator firstRow(PersistentStore store) {

        store.readLock();

        try {
            NodeAVL x = getAccessor(store);
            NodeAVL l = x;

            while (l != null) {
                x = l;
                l = x.getLeft(store);
            }

            if (x == null) {
                return emptyIterator;
            }

            return new IndexRowIterator(null, store, this, x, 0, false, false);
        } finally {
            store.readUnlock();
        }
    }

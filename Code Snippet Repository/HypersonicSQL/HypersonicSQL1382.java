    IndexRowIterator getIterator(Session session, PersistentStore store,
                                 NodeAVL x, boolean single, boolean reversed) {

        if (x == null) {
            return emptyIterator;
        } else {
            IndexRowIterator it = new IndexRowIterator(session, store, this,
                x, 0, single, reversed);

            return it;
        }
    }

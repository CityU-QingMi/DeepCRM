    public final boolean isEmpty(Session session) {

        if (getIndexCount() == 0) {
            return true;
        }

        PersistentStore store = getRowStore(session);

        return getIndex(0).isEmpty(store);
    }

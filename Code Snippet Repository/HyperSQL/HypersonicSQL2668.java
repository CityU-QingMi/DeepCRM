    synchronized public void resetAccessorKeys(Session session, Table table,
            Index[] indexes) {

        PersistentStore store = findStore(table);

        if (store == null) {
            return;
        }

        store.resetAccessorKeys(session, indexes);
    }

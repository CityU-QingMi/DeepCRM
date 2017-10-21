    synchronized public void removeStore(TableBase table) {

        PersistentStore store =
            (PersistentStore) rowStoreMap.get(table.getPersistenceId());

        if (store != null) {
            store.removeAll();
            store.release();
            rowStoreMap.remove(table.getPersistenceId());
        }
    }

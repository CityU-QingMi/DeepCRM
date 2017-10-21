    synchronized public PersistentStore getStore(TableBase table) {

        long persistenceId = table.getPersistenceId();
        PersistentStore store =
            (PersistentStore) rowStoreMap.get(persistenceId);

        if (store == null) {
            store = database.logger.newStore(null, this, table);

            rowStoreMap.put(persistenceId, store);

            table.store = store;
        }

        return store;
    }

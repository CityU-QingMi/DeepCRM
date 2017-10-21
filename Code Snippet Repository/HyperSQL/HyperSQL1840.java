    public void release() {

        if (rowStoreMap.isEmpty()) {
            return;
        }

        Iterator it = rowStoreMap.values().iterator();

        while (it.hasNext()) {
            PersistentStore store = (PersistentStore) it.next();

            store.release();
        }

        rowStoreMap.clear();
    }

    synchronized public void clearResultTables(long actionTimestamp) {

        if (rowStoreMapSession.isEmpty()) {
            return;
        }

        Iterator it = rowStoreMapSession.values().iterator();

        while (it.hasNext()) {
            PersistentStore store = (PersistentStore) it.next();

            if (store.getTimestamp() == actionTimestamp) {
                store.release();
                it.remove();
            }
        }
    }

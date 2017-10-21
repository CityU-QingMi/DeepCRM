    synchronized public void clearRoutineTables() {

        if (rowStoreMapRoutine.isEmpty()) {
            return;
        }

        Iterator it = rowStoreMapRoutine.values().iterator();

        while (it.hasNext()) {
            PersistentStore store = (PersistentStore) it.next();

            store.release();
        }

        rowStoreMapRoutine.clear();
    }

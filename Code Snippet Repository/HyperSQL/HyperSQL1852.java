    synchronized public void clearStatementTables() {

        if (rowStoreMapStatement.isEmpty()) {
            return;
        }

        Iterator it = rowStoreMapStatement.values().iterator();

        while (it.hasNext()) {
            PersistentStore store = (PersistentStore) it.next();

            store.release();
        }

        rowStoreMapStatement.clear();
    }

    synchronized public void moveData(Table oldTable, Table newTable,
                                      int colIndex, int adjust) {

        PersistentStore store = findStore(oldTable);

        if (store == null) {
            return;
        }

        PersistentStore newStore = getStore(newTable);

        try {
            newStore.moveData(session, store, colIndex, adjust);
        } catch (HsqlException e) {
            newStore.release();
            removeStore(newTable);

            throw e;
        }

        removeStore(oldTable);
    }

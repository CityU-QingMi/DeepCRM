    synchronized public void pop(boolean isRoutine) {

        Object[] array;

        if (isRoutine) {
            array = (Object[]) rowStoreListStack.removeLast();

            clearRoutineTables();

            for (int i = 0; i < array.length; i++) {
                PersistentStore store = (PersistentStore) array[i];

                rowStoreMapRoutine.put(store.getTable().getPersistenceId(),
                                       store);
            }
        }

        array = (Object[]) rowStoreListStack.removeLast();

        clearStatementTables();

        for (int i = 0; i < array.length; i++) {
            PersistentStore store = (PersistentStore) array[i];

            rowStoreMapStatement.put(store.getTable().getPersistenceId(),
                                     store);
        }
    }

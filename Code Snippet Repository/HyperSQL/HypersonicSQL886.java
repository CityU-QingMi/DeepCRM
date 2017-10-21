    public PersistentStore getNewResultRowStore(TableBase table,
            boolean isCached) {

        try {
            PersistentStore store = persistentStoreCollection.getStore(table);

            if (!isCached) {
                store.setMemory(true);
            }

            return store;
        } catch (HsqlException e) {}

        throw Error.runtimeError(ErrorCode.U_S0500, "SessionData");
    }

    public PersistentStore getRowStore(Session session) {

        if (store != null) {
            return store;
        }

        if (isSessionBased) {
            return session.sessionData.persistentStoreCollection.getStore(
                this);
        }

        return database.persistentStoreCollection.getStore(this);
    }

    public void disconnect() {

        this.store = null;

        PersistentStore store =
            database.persistentStoreCollection.getStore(this);

        store.release();

        isConnected = false;
    }

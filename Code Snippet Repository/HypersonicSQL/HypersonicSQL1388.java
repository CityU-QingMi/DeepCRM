    public boolean isEmpty(PersistentStore store) {

        store.readLock();

        try {
            return getAccessor(store) == null;
        } finally {
            store.readUnlock();
        }
    }

    public void setHeader(String header) {

        PersistentStore store =
            database.persistentStoreCollection.getStore(this);
        TextCache cache = (TextCache) store.getCache();

        if (cache != null && cache.isIgnoreFirstLine()) {
            cache.setHeader(header);

            return;
        }

        throw Error.error(ErrorCode.TEXT_TABLE_HEADER);
    }

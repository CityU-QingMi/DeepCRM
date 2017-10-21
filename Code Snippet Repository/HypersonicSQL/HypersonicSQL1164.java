    private String getHeader() {

        PersistentStore store =
            database.persistentStoreCollection.getStore(this);
        TextCache cache  = (TextCache) store.getCache();
        String    header = cache == null ? null
                                         : cache.getHeader();

        return header == null ? null
                              : StringConverter.toQuotedString(header, '\'',
                              true);
    }

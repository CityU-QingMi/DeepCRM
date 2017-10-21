    public DataFileCache openTextFilePersistence(Table table, String source,
            boolean readOnlyData, boolean reversed) {

        closeTextCache(table);

        TextCache c = new TextCache(table, source);

        c.open(readOnlyData || reversed);
        textCacheList.put(table.getName(), c);

        return c;
    }

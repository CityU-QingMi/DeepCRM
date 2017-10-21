    public void closeTextCache(Table table) {

        TextCache c = (TextCache) textCacheList.remove(table.getName());

        if (c != null) {
            try {
                c.close();
            } catch (HsqlException e) {}
        }
    }

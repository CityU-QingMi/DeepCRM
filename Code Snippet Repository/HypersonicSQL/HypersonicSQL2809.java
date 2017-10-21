    public void closeAllTextCaches(boolean delete) {

        Iterator it = textCacheList.values().iterator();

        while (it.hasNext()) {
            TextCache textCache = ((TextCache) it.next());

            // use textCache.table to cover both cache and table readonly
            if (delete && !textCache.table.isDataReadOnly()) {
                textCache.purge();
            } else {
                textCache.close();
            }
        }
    }

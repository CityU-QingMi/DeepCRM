    public void setNewTableSpaces() {

        DataFileCache dataCache = database.logger.getCache();

        if (dataCache == null) {
            return;
        }

        Iterator it = rowStoreMap.values().iterator();

        while (it.hasNext()) {
            PersistentStore store = (PersistentStore) it.next();

            if (store == null) {
                continue;
            }

            TableBase table = store.getTable();

            if (table.getTableType() == TableBase.CACHED_TABLE) {
                TableSpaceManager tableSpace =
                    dataCache.spaceManager.getTableSpace(table.getSpaceID());

                store.setSpaceManager(tableSpace);
            }
        }
    }

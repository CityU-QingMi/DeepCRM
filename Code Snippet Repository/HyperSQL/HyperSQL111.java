    public void setIndexRoots(long[] roots) {

        if (!isCached) {
            throw Error.error(ErrorCode.X_42501, tableName.name);
        }

        PersistentStore store =
            database.persistentStoreCollection.getStore(this);

        for (int index = 0; index < indexList.length; index++) {
            store.setAccessor(indexList[index], roots[index]);
        }
    }

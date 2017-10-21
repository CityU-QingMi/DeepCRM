    public final long[] getIndexRootsArray() {

        PersistentStore store =
            database.persistentStoreCollection.getStore(this);
        long[] roots = new long[indexList.length];

        for (int index = 0; index < indexList.length; index++) {
            CachedObject accessor = store.getAccessor(indexList[index]);

            roots[index] = accessor == null ? -1
                                            : accessor.getPos();
        }

        return roots;
    }

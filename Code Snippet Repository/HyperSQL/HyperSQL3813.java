    public void setIndexRoots(long[][] roots) {

        readLock.lock();

        try {
            HsqlArrayList allTables =
                database.schemaManager.getAllTables(true);

            for (int i = 0, size = allTables.size(); i < size; i++) {
                Table t = (Table) allTables.get(i);

                if (t.getTableType() == TableBase.CACHED_TABLE) {
                    long[] rootsArray = roots[i];

                    if (rootsArray != null) {
                        t.setIndexRoots(rootsArray);
                    }
                }
            }
        } finally {
            readLock.unlock();
        }
    }

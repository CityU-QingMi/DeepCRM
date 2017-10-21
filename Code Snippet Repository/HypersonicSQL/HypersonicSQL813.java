    public long[][] getIndexRoots(Session session) {

        readLock.lock();

        try {
            if (tempIndexRoots != null) {
                long[][] roots = tempIndexRoots;

                tempIndexRoots = null;

                return roots;
            }

            HsqlArrayList allTables = getAllTables(true);
            HsqlArrayList list      = new HsqlArrayList();

            for (int i = 0, size = allTables.size(); i < size; i++) {
                Table t = (Table) allTables.get(i);

                if (t.getTableType() == TableBase.CACHED_TABLE) {
                    long[] roots = t.getIndexRootsArray();

                    list.add(roots);
                } else {
                    list.add(null);
                }
            }

            long[][] array = new long[list.size()][];

            list.toArray(array);

            return array;
        } finally {
            readLock.unlock();
        }
    }

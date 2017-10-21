    public void add(Session session, CachedObject object, boolean tx) {

        cache.writeLock.lock();

        try {
            int size = object.getRealSize(cache.rowOut);

            object.setStorageSize(size);

            long pos = tableSpace.getFilePosition(size, false);

            object.setPos(pos);

            if (tx) {
                RowAction.addInsertAction(session, table, (Row) object);
            }

            cache.add(object, false);
        } finally {
            cache.writeLock.unlock();
        }
    }

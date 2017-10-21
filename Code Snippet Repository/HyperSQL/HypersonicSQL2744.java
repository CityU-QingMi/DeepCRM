    public Object[] getData(RowAVLDiskData row) {

        cache.writeLock.lock();

        try {
            cache.get(row, this, false);

            return row.getData();
        } finally {
            cache.writeLock.unlock();
        }
    }

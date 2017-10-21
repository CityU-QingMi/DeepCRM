    public RowStoreAVLDisk(DataFileCache cache, Table table) {

        this(table);

        this.cache = cache;
        rowOut     = cache.rowOut.duplicate();

        cache.adjustStoreCount(1);

        largeData  = database.logger.propLargeData;
        tableSpace = cache.spaceManager.getTableSpace(table.getSpaceID());
        lock       = new ReentrantReadWriteLock();
        readLock   = lock.readLock();
        writeLock  = lock.writeLock();
    }

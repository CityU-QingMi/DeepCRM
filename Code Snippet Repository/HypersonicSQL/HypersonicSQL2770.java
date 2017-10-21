    public RowStoreAVLMemory(Table table) {

        this.database     = table.database;
        this.table        = table;
        this.indexList    = table.getIndexList();
        this.accessorList = new CachedObject[indexList.length];
        lock              = new ReentrantReadWriteLock();
        readLock          = lock.readLock();
        writeLock         = lock.writeLock();
    }

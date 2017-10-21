    private void putNoCheck(CachedObject row) {

        if (accessCount > ACCESS_MAX) {
            updateAccessCounts();
            resetAccessCount();
            updateObjectAccessCounts();
        }

        Object existing = addOrRemoveObject(row, row.getPos(), false);

        if (existing != null) {
            dataFileCache.logSevereEvent("existing object in Cache.put() "
                                         + row.getPos() + " "
                                         + row.getStorageSize(), null);
        }

        row.setInMemory(true);

        cacheBytesLength += row.getStorageSize();
    }

    void putUsingReserve(CachedObject row) {

        int storageSize = row.getStorageSize();

        preparePut(storageSize);

        if (size() >= capacity) {
            throw Error.error(ErrorCode.DATA_CACHE_IS_FULL,
                              String.valueOf(capacity));
        }

        putNoCheck(row);
    }

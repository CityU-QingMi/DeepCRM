    void put(CachedObject row) {

        int storageSize = row.getStorageSize();

        if (preparePut(storageSize)) {
            putNoCheck(row);
        } else {
            long value = size() + reserveCount >= capacity ? capacity
                                                           : bytesCapacity
                                                             / 1024L;

            throw Error.error(ErrorCode.DATA_CACHE_IS_FULL,
                              String.valueOf(value));
        }
    }

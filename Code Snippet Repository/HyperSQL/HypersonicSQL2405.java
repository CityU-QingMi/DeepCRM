    boolean preparePut(int storageSize) {

        boolean exceedsCount = size() + reserveCount >= capacity;
        boolean exceedsSize  = storageSize + cacheBytesLength > bytesCapacity;

        if (exceedsCount || exceedsSize) {
            cleanUp(false);

            exceedsCount = size() + reserveCount >= capacity;
            exceedsSize  = storageSize + cacheBytesLength > bytesCapacity;

            if (exceedsCount || exceedsSize) {
                clearUnchanged();
            } else {
                return true;
            }

            exceedsCount = size() + reserveCount >= capacity;
            exceedsSize  = storageSize + cacheBytesLength > bytesCapacity;

            if (exceedsCount || exceedsSize) {
                cleanUp(true);
            } else {
                return true;
            }

            exceedsCount = size() + reserveCount >= capacity;
            exceedsSize  = storageSize + cacheBytesLength > bytesCapacity;

            if (exceedsCount) {
                dataFileCache.logInfoEvent(
                    "dataFileCache CACHE ROWS limit reached");
            }

            if (exceedsSize) {
                dataFileCache.logInfoEvent(
                    "dataFileCache CACHE SIZE limit reached");
            }

            if (exceedsCount || exceedsSize) {
                return false;
            }
        }

        return true;
    }

    public long getFileBlocks(int tableId, int blockCount) {

        cache.writeLock.lock();

        try {
            long index = getExistingBlockIndex(tableId, blockCount);

            if (index > 0) {
                return index * fileBlockSize;
            } else {
                return getNewFileBlocks(tableId, blockCount);
            }
        } finally {
            cache.writeLock.unlock();
        }
    }

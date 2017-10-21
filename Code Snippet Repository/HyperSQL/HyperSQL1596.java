    public void releaseRange(IntIndex list, int fileBlockItemCount) {

        writeLock.lock();

        try {
            cacheModified = true;

            cache.releaseRange(list, fileBlockItemCount);
        } finally {
            writeLock.unlock();
        }
    }

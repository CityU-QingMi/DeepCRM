    public CachedObject release(long pos) {

        writeLock.lock();

        try {
            cacheModified = true;

            return cache.release(pos);
        } finally {
            writeLock.unlock();
        }
    }

    public void releaseRange(long startPos, long limitPos) {

        writeLock.lock();

        try {
            cacheModified = true;

            cache.releaseRange(startPos, limitPos);
        } finally {
            writeLock.unlock();
        }
    }

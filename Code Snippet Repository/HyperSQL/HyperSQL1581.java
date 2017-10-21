    protected void clear() {

        writeLock.lock();

        try {
            cache.clear();
        } finally {
            writeLock.unlock();
        }
    }

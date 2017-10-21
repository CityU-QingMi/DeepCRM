    public void addInit(CachedObject object) {

        writeLock.lock();

        try {
            cache.put(object);
        } finally {
            writeLock.unlock();
        }
    }

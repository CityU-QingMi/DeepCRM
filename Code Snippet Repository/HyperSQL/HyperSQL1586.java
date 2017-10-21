    public void add(CachedObject object, boolean keep) {

        writeLock.lock();

        try {
            cacheModified = true;

            cache.put(object);

            if (keep) {
                object.keepInMemory(true);
            }

            if (object.getStorageSize() > initIOBufferSize) {
                rowOut.reset(object.getStorageSize());
            }
        } finally {
            writeLock.unlock();
        }
    }

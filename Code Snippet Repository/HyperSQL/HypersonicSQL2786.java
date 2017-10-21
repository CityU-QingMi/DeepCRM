    public void add(CachedObject object, boolean keep) {

        writeLock.lock();

        try {
            uncommittedCache.put(object.getPos(), object);
        } finally {
            writeLock.unlock();
        }
    }

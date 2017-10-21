    public void remove(CachedObject object) {

        writeLock.lock();

        try {
            long         pos = object.getPos();
            CachedObject row = (CachedObject) uncommittedCache.remove(pos);

            if (row != null) {
                return;
            }

            cache.release(pos);
        } finally {
            writeLock.unlock();
        }
    }

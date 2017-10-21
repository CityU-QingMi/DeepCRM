    public void removePersistence(CachedObject row) {

        writeLock.lock();

        try {
            clearRowImage(row);
        } finally {
            writeLock.unlock();
        }
    }

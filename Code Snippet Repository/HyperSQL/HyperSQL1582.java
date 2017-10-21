    public void adjustStoreCount(int adjust) {

        writeLock.lock();

        try {
            storeCount += adjust;
        } finally {
            writeLock.unlock();
        }
    }

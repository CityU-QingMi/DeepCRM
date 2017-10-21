    public void adjustStoreCount(int adjust) {

        writeLock.lock();

        try {
            storeCount += adjust;

            if (storeCount == 0) {
                clear();
            }
        } finally {
            writeLock.unlock();
        }
    }

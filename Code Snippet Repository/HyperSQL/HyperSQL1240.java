    public boolean containsKey(long key) {

        readLock.lock();

        try {
            return super.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

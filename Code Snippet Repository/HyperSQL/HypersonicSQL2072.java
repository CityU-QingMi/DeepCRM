    public boolean containsKey(int key) {

        try {
            readLock.lock();

            return super.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

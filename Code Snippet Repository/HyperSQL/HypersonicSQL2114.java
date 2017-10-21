    public void clear() {

        writeLock.lock();

        try {
            super.clear();
        } finally {
            writeLock.unlock();
        }
    }

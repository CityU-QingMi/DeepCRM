    public Object remove(long key) {

        writeLock.lock();

        try {
            return super.addOrRemove(key, 0, null, null, true);
        } finally {
            writeLock.unlock();
        }
    }

    public Object put(long key, Object value) {

        writeLock.lock();

        try {
            return super.addOrRemove(key, 0, null, value, false);
        } finally {
            writeLock.unlock();
        }
    }

    public Object put(int key, Object value) {

        try {
            writeLock.lock();

            return super.addOrRemove(key, 0, null, value, false);
        } finally {
            writeLock.unlock();
        }
    }

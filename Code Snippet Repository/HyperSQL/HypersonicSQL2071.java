    public Object remove(int key) {

        try {
            writeLock.lock();

            return super.addOrRemove(key, 0, null, null, true);
        } finally {
            writeLock.unlock();
        }
    }

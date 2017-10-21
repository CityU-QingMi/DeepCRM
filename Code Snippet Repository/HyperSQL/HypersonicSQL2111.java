    public boolean containsValue(Object value) {

        readLock.lock();

        try {
            return super.containsValue(value);
        } finally {
            readLock.unlock();
        }
    }

    public boolean containsValue(Object value) {

        try {
            readLock.lock();

            return super.containsValue(value);
        } finally {
            readLock.unlock();
        }
    }

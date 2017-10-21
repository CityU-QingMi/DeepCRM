    public boolean schemaExists(String name) {

        readLock.lock();

        try {
            return schemaMap.containsKey(name);
        } finally {
            readLock.unlock();
        }
    }

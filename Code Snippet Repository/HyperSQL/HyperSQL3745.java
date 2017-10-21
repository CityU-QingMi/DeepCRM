    public Schema findSchema(String name) {

        readLock.lock();

        try {
            return ((Schema) schemaMap.get(name));
        } finally {
            readLock.unlock();
        }
    }

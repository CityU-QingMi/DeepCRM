    public Iterator databaseObjectIterator(String schemaName, int type) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(schemaName);

            return schema.schemaObjectIterator(type);
        } finally {
            readLock.unlock();
        }
    }

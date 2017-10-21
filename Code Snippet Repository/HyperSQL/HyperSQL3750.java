    public HashMappedList getTables(String schema) {

        readLock.lock();

        try {
            Schema temp = (Schema) schemaMap.get(schema);

            return temp.tableList;
        } finally {
            readLock.unlock();
        }
    }

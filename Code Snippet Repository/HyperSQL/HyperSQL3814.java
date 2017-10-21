    public Schema[] getAllSchemas() {

        readLock.lock();

        try {
            Schema[] objects = new Schema[schemaMap.size()];

            schemaMap.toValuesArray(objects);

            return objects;
        } finally {
            readLock.unlock();
        }
    }

    public SchemaObject findSchemaObject(String name, String schemaName,
                                         int type) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(schemaName);

            if (schema == null) {
                return null;
            }

            return schema.findSchemaObject(name, type);
        } finally {
            readLock.unlock();
        }
    }

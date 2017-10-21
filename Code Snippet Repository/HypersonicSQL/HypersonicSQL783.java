    public SchemaObject findAnySchemaObject(String name, String schemaName) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(schemaName);

            if (schema == null) {
                return null;
            }

            return schema.findAnySchemaObject(name);
        } finally {
            readLock.unlock();
        }
    }

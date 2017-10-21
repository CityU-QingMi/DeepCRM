    public SchemaObject getSchemaObject(String name, String schemaName,
                                        int type) {

        readLock.lock();

        try {
            SchemaObject object = findSchemaObject(name, schemaName, type);

            if (object == null) {
                throw Error.error(SchemaObjectSet.getGetErrorCode(type), name);
            }

            return object;
        } finally {
            readLock.unlock();
        }
    }

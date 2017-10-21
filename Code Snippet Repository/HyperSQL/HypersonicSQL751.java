    public HsqlName findSchemaHsqlName(String name) {

        readLock.lock();

        try {
            Schema schema = ((Schema) schemaMap.get(name));

            if (schema == null) {
                return null;
            }

            return schema.getName();
        } finally {
            readLock.unlock();
        }
    }

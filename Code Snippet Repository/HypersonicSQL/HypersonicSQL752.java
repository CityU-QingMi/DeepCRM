    public HsqlName getSchemaHsqlName(String name) {

        if (name == null) {
            return defaultSchemaHsqlName;
        }

        readLock.lock();

        try {
            Schema schema = ((Schema) schemaMap.get(name));

            if (schema == null) {
                throw Error.error(ErrorCode.X_3F000, name);
            }

            return schema.getName();
        } finally {
            readLock.unlock();
        }
    }

    public HsqlName getUserSchemaHsqlName(String name) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(name);

            if (schema == null) {
                throw Error.error(ErrorCode.X_3F000, name);
            }

            if (schema.getName()
                    == SqlInvariants.INFORMATION_SCHEMA_HSQLNAME) {
                throw Error.error(ErrorCode.X_3F000, name);
            }

            return schema.getName();
        } finally {
            readLock.unlock();
        }
    }

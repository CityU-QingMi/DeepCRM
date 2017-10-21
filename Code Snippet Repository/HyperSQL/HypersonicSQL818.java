    public Grantee toSchemaOwner(String name) {

        readLock.lock();

        try {
            Schema schema = (Schema) schemaMap.get(name);

            return schema == null ? null
                                  : schema.getOwner();
        } finally {
            readLock.unlock();
        }
    }

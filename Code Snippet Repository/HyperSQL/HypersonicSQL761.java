    public void checkSchemaObjectNotExists(HsqlName name) {

        readLock.lock();

        try {
            Schema          schema = (Schema) schemaMap.get(name.schema.name);
            SchemaObjectSet set    = getSchemaObjectSet(schema, name.type);

            set.checkAdd(name);
        } finally {
            readLock.unlock();
        }
    }

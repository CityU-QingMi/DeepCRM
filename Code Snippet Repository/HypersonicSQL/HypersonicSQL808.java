    public void renameSchemaObject(HsqlName name, HsqlName newName) {

        writeLock.lock();

        try {
            if (name.schema != newName.schema) {
                throw Error.error(ErrorCode.X_42505, newName.schema.name);
            }

            checkObjectIsReferenced(name);

            Schema          schema = (Schema) schemaMap.get(name.schema.name);
            SchemaObjectSet set    = getSchemaObjectSet(schema, name.type);

            set.rename(name, newName);
        } finally {
            writeLock.unlock();
        }
    }

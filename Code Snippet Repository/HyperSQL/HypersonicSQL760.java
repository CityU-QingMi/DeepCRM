    public void createPublicSchema() {

        writeLock.lock();

        try {
            HsqlName name = database.nameManager.newHsqlName(null,
                SqlInvariants.PUBLIC_SCHEMA, SchemaObject.SCHEMA);
            Schema schema =
                new Schema(name, database.getGranteeManager().getDBARole());

            defaultSchemaHsqlName = schema.getName();

            schemaMap.put(schema.getName().name, schema);
        } finally {
            writeLock.unlock();
        }
    }

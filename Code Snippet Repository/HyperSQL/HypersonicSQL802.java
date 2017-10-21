    public HsqlName getSchemaObjectName(HsqlName schemaName, String name,
                                        int type, boolean raise) {

        readLock.lock();

        try {
            Schema          schema = (Schema) schemaMap.get(schemaName.name);
            SchemaObjectSet set    = null;

            if (schema == null) {
                if (raise) {
                    throw Error.error(SchemaObjectSet.getGetErrorCode(type));
                } else {
                    return null;
                }
            }

            if (type == SchemaObject.ROUTINE) {
                set = schema.functionLookup;

                SchemaObject object = schema.functionLookup.getObject(name);

                if (object == null) {
                    set    = schema.procedureLookup;
                    object = schema.procedureLookup.getObject(name);
                }
            } else {
                set = getSchemaObjectSet(schema, type);
            }

            if (raise) {
                set.checkExists(name);
            }

            return set.getName(name);
        } finally {
            readLock.unlock();
        }
    }

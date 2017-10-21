    SchemaObject readSchemaObjectName(int type) {

        checkIsSchemaObjectName();
        checkValidCatalogName(token.namePrePrefix);

        String schema = session.getSchemaName(token.namePrefix);
        SchemaObject object =
            database.schemaManager.getSchemaObject(token.tokenString, schema,
                type);

        read();

        return object;
    }

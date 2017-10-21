    SchemaObject readSchemaObjectName(HsqlName schemaName, int type) {

        checkIsSchemaObjectName();

        SchemaObject object =
            database.schemaManager.getSchemaObject(token.tokenString,
                schemaName.name, type);

        if (token.namePrefix != null) {
            if (!token.namePrefix.equals(schemaName.name)) {

                // todo - better error message
                throw Error.error(ErrorCode.X_42505, token.namePrefix);
            }

            if (token.namePrePrefix != null) {
                if (!token.namePrePrefix.equals(
                        database.getCatalogName().name)) {

                    // todo - better error message
                    throw Error.error(ErrorCode.X_42505, token.namePrefix);
                }
            }
        }

        read();

        return object;
    }

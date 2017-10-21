    private Statement compileAlterView() {

        int position = getPosition();

        read();

        String   tableName = token.tokenString;
        HsqlName schema    = session.getSchemaHsqlName(token.namePrefix);

        checkSchemaUpdateAuthorisation(schema);

        Table t = database.schemaManager.getUserTable(tableName, schema.name);

        read();

        switch (token.tokenType) {

            case Tokens.RENAME : {
                read();
                readThis(Tokens.TO);

                return compileRenameObject(t.getName(), SchemaObject.VIEW);
            }
            case Tokens.AS : {
                rewind(position);

                return compileCreateView(true, false);
            }
        }

        throw unexpectedToken();
    }

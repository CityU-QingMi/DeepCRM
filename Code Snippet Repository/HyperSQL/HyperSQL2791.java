    StatementSchema compileCreateCharacterSet() {

        read();
        readThis(Tokens.SET);

        HsqlName name = readNewSchemaObjectName(SchemaObject.CHARSET, false);

        readIfThis(Tokens.AS);
        readThis(Tokens.GET);

        String schema = token.namePrefix;
        Charset source =
            (Charset) database.schemaManager.getCharacterSet(session,
                token.tokenString, schema);

        read();

        if (token.tokenType == Tokens.COLLATION) {
            read();
            readThis(Tokens.FROM);
            readThis(Tokens.DEFAULT);
        }

        Charset charset = new Charset(name);

        charset.base = source.getName();

        String     sql            = getLastPart();
        Object[]   args           = new Object[]{ charset };
        HsqlName[] writeLockNames = database.schemaManager.catalogNameArray;

        return new StatementSchema(sql, StatementTypes.CREATE_CHARACTER_SET,
                                   args, null, writeLockNames);
    }

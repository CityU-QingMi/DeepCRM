    StatementSchema compileCreateType(boolean orReplace) {

        read();

        HsqlName name = readNewSchemaObjectName(SchemaObject.TYPE, false);

        readThis(Tokens.AS);

        Type type = readTypeDefinition(true, false).duplicate();

        readIfThis(Tokens.FINAL);

        UserTypeModifier userTypeModifier = new UserTypeModifier(name,
            SchemaObject.TYPE, type);

        type.userTypeModifier = userTypeModifier;

        String     sql            = getLastPart();
        Object[]   args           = new Object[]{ type };
        HsqlName[] writeLockNames = database.schemaManager.catalogNameArray;

        return new StatementSchema(sql, StatementTypes.CREATE_TYPE, args,
                                   null, writeLockNames);
    }

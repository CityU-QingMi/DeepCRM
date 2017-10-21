    StatementSchema compileCreateRole() {

        read();

        HsqlName   name           = readNewUserIdentifier();
        String     sql            = getLastPart();
        Object[]   args           = new Object[]{ name };
        HsqlName[] writeLockNames = database.schemaManager.catalogNameArray;

        return new StatementSchema(sql, StatementTypes.CREATE_ROLE, args,
                                   null, writeLockNames);
    }

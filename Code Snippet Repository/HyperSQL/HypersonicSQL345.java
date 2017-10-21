    private Statement compileAlterTableDropPrimaryKey(Table table) {

        boolean cascade = false;

        if (token.tokenType == Tokens.RESTRICT) {
            read();
        } else if (token.tokenType == Tokens.CASCADE) {
            read();

            cascade = true;
        }

        if (!table.hasPrimaryKey()) {
            throw Error.error(ErrorCode.X_42501);
        }

        String       sql    = getLastPart();
        SchemaObject object = table.getPrimaryConstraint();
        Object[]     args   = new Object[] {
            object.getName(), ValuePool.getInt(SchemaObject.CONSTRAINT),
            Boolean.valueOf(cascade), Boolean.FALSE
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());
        Statement cs = new StatementSchema(sql,
                                           StatementTypes.DROP_CONSTRAINT,
                                           args, null, writeLockNames);

        return cs;
    }

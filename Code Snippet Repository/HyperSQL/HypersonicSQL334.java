    private Statement compileAlterTableDropConstraint(Table table) {

        boolean cascade = false;
        SchemaObject object = readSchemaObjectName(table.getSchemaName(),
            SchemaObject.CONSTRAINT);

        if (token.tokenType == Tokens.RESTRICT) {
            read();
        } else if (token.tokenType == Tokens.CASCADE) {
            read();

            cascade = true;
        }

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            object.getName(), ValuePool.getInt(SchemaObject.CONSTRAINT),
            Boolean.valueOf(cascade), Boolean.FALSE
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());
        HsqlName mainTableName = ((Constraint) object).getMainTableName();

        if (mainTableName != null && mainTableName != table.getName()) {
            writeLockNames =
                (HsqlName[]) ArrayUtil.toAdjustedArray(writeLockNames,
                    mainTableName, writeLockNames.length, 1);
        }

        Statement cs = new StatementSchema(sql,
                                           StatementTypes.DROP_CONSTRAINT,
                                           args, null, writeLockNames);

        return cs;
    }

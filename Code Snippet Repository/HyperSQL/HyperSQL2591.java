    Statement compileAlterTableDropColumn(Table table, String colName,
                                          boolean cascade) {

        int colindex = table.getColumnIndex(colName);

        if (table.getColumnCount() == 1) {
            throw Error.error(ErrorCode.X_42591);
        }

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            table.getColumn(colindex).getName(),
            ValuePool.getInt(SchemaObject.COLUMN), Boolean.valueOf(cascade),
            Boolean.FALSE
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.DROP_COLUMN, args,
                                   null, writeLockNames);
    }

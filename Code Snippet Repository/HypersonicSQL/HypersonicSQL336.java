    private Statement compileAlterColumnSetNullability(Table table,
            ColumnSchema column, boolean nullable) {

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            StatementTypes.ALTER_COLUMN_NULL, table, column,
            Boolean.valueOf(nullable)
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

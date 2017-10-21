    private Statement compileAlterColumnDefault(Table table,
            ColumnSchema column, int columnIndex) {

        //ALTER TABLE .. ALTER COLUMN .. SET DEFAULT
        Type       type = column.getDataType();
        Expression expr = readDefaultClause(type);
        String     sql  = getLastPart();
        Object[]   args = new Object[] {
            StatementTypes.ALTER_COLUMN_DEFAULT, table, column,
            Integer.valueOf(columnIndex), expr
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

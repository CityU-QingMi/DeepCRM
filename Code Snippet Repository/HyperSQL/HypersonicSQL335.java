    private Statement compileAlterColumnDataType(Table table,
            ColumnSchema column) {

        if (column.isGenerated()) {
            throw Error.error(ErrorCode.X_42561);
        }

        Type type = readTypeDefinition(true, true);

        if (column.isIdentity()) {
            if (!type.isIntegralType()) {
                throw Error.error(ErrorCode.X_42561);
            }
        }

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            StatementTypes.ALTER_COLUMN_TYPE, table, column, type
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

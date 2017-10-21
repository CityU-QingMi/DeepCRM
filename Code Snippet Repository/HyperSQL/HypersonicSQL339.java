    StatementSchema compileAlterColumnAddSequence(Table table,
            ColumnSchema column, int colIndex) {

        if (!column.getDataType().isIntegralType()) {
            throw Error.error(ErrorCode.X_42525);
        }

        if (column.isIdentity()) {
            throw Error.error(ErrorCode.X_42525);
        }

        NumberSequence sequence = readSequence(column);
        String         sql      = getLastPart();
        Object[]       args     = new Object[] {
            StatementTypes.ALTER_COLUMN_SEQUENCE, table, column,
            Integer.valueOf(colIndex), sequence
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

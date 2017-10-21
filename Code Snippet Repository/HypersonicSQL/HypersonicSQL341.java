    private Statement compileAlterColumnRename(Table table,
            ColumnSchema column) {

        checkIsSimpleName();

        HsqlName name = readNewSchemaObjectName(SchemaObject.COLUMN, true);

        if (table.findColumn(name.name) > -1) {
            throw Error.error(ErrorCode.X_42504, name.name);
        }

        database.schemaManager.checkColumnIsReferenced(table.getName(),
                column.getName());

        String     sql            = getLastPart();
        Object[]   args           = new Object[] {
            column.getName(), name
        };
        HsqlName[] writeLockNames = new HsqlName[] {
            database.getCatalogName(), table.getName()
        };

        return new StatementSchema(sql, StatementTypes.RENAME_OBJECT, args,
                                   null, writeLockNames);
    }

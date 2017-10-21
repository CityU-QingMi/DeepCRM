    Statement compileAlterTableAddForeignKeyConstraint(Table table,
            HsqlName name, Boolean ifNotExists) {

        if (name == null) {
            name = database.nameManager.newAutoName("FK",
                    table.getSchemaName(), table.getName(),
                    SchemaObject.CONSTRAINT);
        }

        OrderedHashSet set           = readColumnNames(false);
        Constraint     c             = readFKReferences(table, name, set);
        HsqlName       mainTableName = c.getMainTableName();

        c.core.mainTable =
            database.schemaManager.getUserTable(mainTableName.name,
                mainTableName.schema.name);

        c.setColumnsIndexes(table);

        if (c.core.mainCols.length != c.core.refCols.length) {
            throw Error.error(ErrorCode.X_42593);
        }

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            StatementTypes.ADD_CONSTRAINT, table, c, ifNotExists
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        if (mainTableName != table.getName()) {
            writeLockNames =
                (HsqlName[]) ArrayUtil.toAdjustedArray(writeLockNames,
                    mainTableName, writeLockNames.length, 1);
        }

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

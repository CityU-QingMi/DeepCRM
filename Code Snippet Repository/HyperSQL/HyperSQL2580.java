    Statement compileAlterTableAddPrimaryKey(Table table, HsqlName name,
            Boolean ifNotExists) {

        if (name == null) {
            name = session.database.nameManager.newAutoName("PK",
                    table.getSchemaName(), table.getName(),
                    SchemaObject.CONSTRAINT);
        }

        OrderedHashSet set = readColumnNames(false);
        Constraint constraint =
            new Constraint(name, set,
                           SchemaObject.ConstraintTypes.PRIMARY_KEY);

        constraint.setColumnsIndexes(table);

        String   sql  = getLastPart();
        Object[] args = new Object[] {
            StatementTypes.ADD_CONSTRAINT, table, constraint, ifNotExists
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

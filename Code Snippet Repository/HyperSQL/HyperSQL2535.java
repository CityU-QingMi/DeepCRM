    Statement compileAlterTableAddUniqueConstraint(Table table, HsqlName name,
            Boolean ifNotExists) {

        if (name == null) {
            name = database.nameManager.newAutoName("CT",
                    table.getSchemaName(), table.getName(),
                    SchemaObject.CONSTRAINT);
        }

        int[] cols = readColumnList(table, false);
        HsqlName indexName =
            session.database.nameManager.newConstraintIndexName(
                table.getName(), name, session.database.sqlSysIndexNames);
        Index index = table.createIndexStructure(indexName, cols, null, null,
            true, true, false);
        Constraint c = new Constraint(name, table, index,
                                      SchemaObject.ConstraintTypes.UNIQUE);
        String   sql  = getLastPart();
        Object[] args = new Object[] {
            StatementTypes.ADD_CONSTRAINT, table, c, ifNotExists
        };
        HsqlName[] writeLockNames =
            database.schemaManager.getCatalogAndBaseTableNames(
                table.getName());

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

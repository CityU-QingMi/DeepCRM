    Statement compileAlterTableAddCheckConstraint(Table table, HsqlName name,
            Boolean ifNotExists) {

        Constraint check;

        if (name == null) {
            name = database.nameManager.newAutoName("CT",
                    table.getSchemaName(), table.getName(),
                    SchemaObject.CONSTRAINT);
        }

        check = new Constraint(name, null, SchemaObject.ConstraintTypes.CHECK);

        readCheckConstraintCondition(check);

        String     sql            = getLastPart();
        Object[]   args           = new Object[] {
            StatementTypes.ADD_CONSTRAINT, table, check, ifNotExists
        };
        HsqlName[] writeLockNames = new HsqlName[] {
            database.getCatalogName(), table.getName()
        };

        return new StatementSchema(sql, StatementTypes.ALTER_TABLE, args,
                                   null, writeLockNames);
    }

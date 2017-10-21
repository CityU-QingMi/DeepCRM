    void addUniqueConstraint(int[] cols, HsqlName name) {

        checkModifyTable(false);
        database.schemaManager.checkSchemaObjectNotExists(name);

        if (table.getUniqueConstraintForColumns(cols) != null) {
            throw Error.error(ErrorCode.X_42522);
        }

        // create an autonamed index
        HsqlName indexname = database.nameManager.newAutoName("IDX",
            name.name, table.getSchemaName(), table.getName(),
            SchemaObject.INDEX);
        Index index = table.createIndexStructure(indexname, cols, null, null,
            true, true, false);
        Constraint constraint =
            new Constraint(name, table, index,
                           SchemaObject.ConstraintTypes.UNIQUE);
        Table tn = table.moveDefinition(session, table.tableType, null,
                                        constraint, index, -1, 0, emptySet,
                                        emptySet);

        moveData(table, tn, -1, 0);

        table = tn;

        database.schemaManager.addSchemaObject(constraint);
        setNewTableInSchema(table);
        updateConstraints(table, emptySet);
        database.schemaManager.recompileDependentObjects(table);
    }

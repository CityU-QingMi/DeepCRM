    void addUniqueConstraint(Constraint constraint) {

        checkModifyTable(false);
        database.schemaManager.checkSchemaObjectNotExists(
            constraint.getName());

        if (table.getUniqueConstraintForColumns(constraint.getMainColumns())
                != null) {
            throw Error.error(ErrorCode.X_42522);
        }

        Table tn = table.moveDefinition(session, table.tableType, null,
                                        constraint, constraint.getMainIndex(),
                                        -1, 0, emptySet, emptySet);

        moveData(table, tn, -1, 0);

        table = tn;

        database.schemaManager.addSchemaObject(constraint);
        setNewTableInSchema(table);
        updateConstraints(table, emptySet);
        database.schemaManager.recompileDependentObjects(table);
    }

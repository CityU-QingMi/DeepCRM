    void addPrimaryKey(Constraint constraint) {

        checkModifyTable(true);

        if (table.hasPrimaryKey()) {
            throw Error.error(ErrorCode.X_42532);
        }

        database.schemaManager.checkSchemaObjectNotExists(
            constraint.getName());

        Table tn = table.moveDefinition(session, table.tableType, null,
                                        constraint, null, -1, 0, emptySet,
                                        emptySet);

        moveData(table, tn, -1, 0);

        table = tn;

        database.schemaManager.addSchemaObject(constraint);
        setNewTableInSchema(table);
        updateConstraints(table, emptySet);
        database.schemaManager.recompileDependentObjects(table);
    }

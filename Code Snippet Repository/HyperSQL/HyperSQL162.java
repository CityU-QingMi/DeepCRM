    private void retypeColumn(ColumnSchema column, int colIndex) {

        Table tn = table.moveDefinition(session, table.tableType, column,
                                        null, null, colIndex, 0, emptySet,
                                        emptySet);

        moveData(table, tn, colIndex, 0);
        setNewTableInSchema(tn);
        updateConstraints(tn, emptySet);
        database.schemaManager.recompileDependentObjects(table);

        table = tn;
    }

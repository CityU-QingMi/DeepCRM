    Index addIndex(int[] col, HsqlName name, boolean unique) {

        Index newIndex;

        checkModifyTable(false);

        if (session.isProcessingScript() || table.isEmpty(session)
                || table.isIndexingMutable()) {
            newIndex = table.createIndex(session, name, col, null, null,
                                         unique, false, false);
        } else {
            newIndex = table.createIndexStructure(name, col, null, null,
                                                  unique, false, false);

            Table tn = table.moveDefinition(session, table.tableType, null,
                                            null, newIndex, -1, 0, emptySet,
                                            emptySet);

            moveData(table, tn, -1, 0);

            table = tn;

            setNewTableInSchema(table);
            updateConstraints(table, emptySet);
        }

        database.schemaManager.addSchemaObject(newIndex);
        database.schemaManager.recompileDependentObjects(table);

        return newIndex;
    }

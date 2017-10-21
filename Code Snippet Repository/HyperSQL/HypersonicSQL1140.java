    void dropIndex(String indexName) {

        Index index;

        checkModifyTable(false);

        index = table.getUserIndex(indexName);

        if (table.isIndexingMutable()) {
            table.dropIndex(session, index.getPosition());
        } else {
            OrderedHashSet indexSet = new OrderedHashSet();

            indexSet.add(table.getIndex(indexName).getName());

            Table tn = table.moveDefinition(session, table.tableType, null,
                                            null, null, -1, 0, emptySet,
                                            indexSet);

            moveData(table, tn, -1, 0);
            setNewTableInSchema(tn);
            updateConstraints(tn, emptySet);

            table = tn;
        }

        if (!index.isConstraint()) {
            database.schemaManager.removeSchemaObject(index.getName());
        }

        database.schemaManager.recompileDependentObjects(table);
    }

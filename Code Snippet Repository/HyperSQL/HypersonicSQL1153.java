    void alterIndex(Index index, int[] columns) {

        PersistentStore store =
            database.persistentStoreCollection.getStore(table);
        int       position  = index.getPosition();
        boolean[] modeFlags = new boolean[columns.length];
        Type[]    colTypes  = new Type[columns.length];

        ArrayUtil.projectRow(table.getColumnTypes(), columns, colTypes);

        Index newIndex = database.logger.newIndex(index.getName(),
            index.getPersistenceId(), table, columns, modeFlags, modeFlags,
            colTypes, false, false, false, false);

        newIndex.setPosition(position);

        table.getIndexList()[position] = newIndex;

        table.setBestRowIdentifiers();

        Index[] indexes = store.getAccessorKeys();

        indexes[position] = newIndex;

        store.reindex(session, newIndex);
        database.schemaManager.recompileDependentObjects(table);
    }

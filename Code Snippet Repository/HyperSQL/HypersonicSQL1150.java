    void moveData(Table oldTable, Table newTable, int colIndex, int adjust) {

        int tableType = oldTable.getTableType();

        if (tableType == Table.TEMP_TABLE) {
            Session sessions[] = database.sessionManager.getAllSessions();

            for (int i = 0; i < sessions.length; i++) {
                sessions[i].sessionData.persistentStoreCollection.moveData(
                    oldTable, newTable, colIndex, adjust);
            }
        } else {
            PersistentStore oldStore =
                database.persistentStoreCollection.getStore(oldTable);
            boolean newSpaceID = false;

            if (newTable.isCached()) {
                newSpaceID = oldTable.getSpaceID()
                             != DataSpaceManager.tableIdDefault;

                if (newSpaceID) {
                    int tableSpaceID =
                        database.logger.getCache().spaceManager
                            .getNewTableSpaceID();

                    newTable.setSpaceID(tableSpaceID);
                }
            }

            PersistentStore newStore =
                database.persistentStoreCollection.getStore(newTable);

            try {
                newStore.moveData(session, oldStore, colIndex, adjust);
            } catch (HsqlException e) {
                database.persistentStoreCollection.removeStore(newTable);

                throw e;
            }

            database.persistentStoreCollection.removeStore(oldTable);
        }
    }

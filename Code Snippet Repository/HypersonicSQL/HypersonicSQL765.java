    private void removeTable(Session session, Table table) {

        database.getGranteeManager().removeDbObject(table.getName());
        table.releaseTriggers();

        if (!table.isView() && table.hasLobColumn()) {
            RowIterator it = table.rowIterator(session);

            while (it.next()) {
                Object[] data = it.getCurrent();

                session.sessionData.adjustLobUsageCount(table, data, -1);
            }
        }

        if (table.tableType == TableBase.TEMP_TABLE) {
            Session[] sessions = database.sessionManager.getAllSessions();

            for (int i = 0; i < sessions.length; i++) {
                sessions[i].sessionData.persistentStoreCollection.removeStore(
                    table);
            }
        } else {
            database.persistentStoreCollection.removeStore(table);
        }
    }

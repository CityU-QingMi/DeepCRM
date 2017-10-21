    public void adjustLobUsageCount(TableBase table, Object[] data,
                                    int adjust) {

        if (!table.hasLobColumn) {
            return;
        }

        if (table.isTemp) {
            return;
        }

        if (session.isProcessingLog() || session.isProcessingScript()) {
            return;
        }

        for (int j = 0; j < table.columnCount; j++) {
            if (table.colTypes[j].isLobType()) {
                Object value = data[j];

                if (value == null) {
                    continue;
                }

                database.lobManager.adjustUsageCount(session,
                                                     ((LobData) value).getId(),
                                                     adjust);

                hasLobOps = true;
            }
        }
    }

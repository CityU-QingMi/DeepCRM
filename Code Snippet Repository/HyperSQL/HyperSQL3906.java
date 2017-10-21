    public void adjustLobUsageCount(LobData value, int adjust) {

        if (session.isProcessingLog() || session.isProcessingScript()) {
            return;
        }

        if (value == null) {
            return;
        }

        database.lobManager.adjustUsageCount(session, value.getId(), adjust);

        hasLobOps = true;
    }

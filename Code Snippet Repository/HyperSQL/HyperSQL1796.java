    private boolean checkpoint() {

        if (filesReadOnly) {
            return true;
        }

        boolean result       = checkpointClose();
        boolean reopenResult = checkpointReopen();

        if (!result) {
            database.logger.logSevereEvent(
                "checkpoint failed - see previous error", null);
        }

        return reopenResult;
    }

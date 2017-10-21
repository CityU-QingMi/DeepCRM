    boolean checkpointReopen() {

        if (filesReadOnly) {
            return true;
        }

        database.sessionManager.resetLoggedSchemas();

        try {
            if (cache != null) {
                cache.reopen();
            }

            if (dbLogWriter != null) {
                openLog();
            }
        } catch (Throwable e) {
            return false;
        }

        return true;
    }

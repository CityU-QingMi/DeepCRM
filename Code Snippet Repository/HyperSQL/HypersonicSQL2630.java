    public boolean close(int closemode) {

        boolean result = true;

        if (log == null) {
            textTableManager.closeAllTextCaches(false);

            return true;
        }

        log.synchLog();
        database.lobManager.synch();

        try {
            switch (closemode) {

                case Database.CLOSEMODE_IMMEDIATELY :
                    log.shutdown();
                    break;

                case Database.CLOSEMODE_NORMAL :
                    log.close(false);
                    break;

                case Database.CLOSEMODE_COMPACT :
                case Database.CLOSEMODE_SCRIPT :
                    log.close(true);
                    break;
            }

            database.persistentStoreCollection.release();
        } catch (Throwable e) {
            database.logger.logSevereEvent("error closing log", e);

            result = false;
        }

        logInfoEvent("Database closed");

        log = null;

        appLog.close();
        sqlLog.close();

        logsStatements = false;
        loggingEnabled = false;

        return result;
    }

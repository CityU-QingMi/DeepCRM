    void closeIfLast() {

        if (sessionManager.isEmpty() && dbState == DATABASE_ONLINE) {
            if (shutdownOnNoConnection) {
                try {
                    close(CLOSEMODE_NORMAL);
                } catch (HsqlException e) {}
            } else {
                logger.synchLog();
            }
        }
    }

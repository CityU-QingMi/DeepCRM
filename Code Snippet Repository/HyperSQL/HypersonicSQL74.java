    void reopen() {

        boolean isNew = false;

        setState(DATABASE_OPENING);

        try {
            createObjectStructures();

            // completed metadata
            logger.open();

            isNew = logger.isNewDatabase;

            if (isNew) {
                String username = urlProperties.getProperty("user", "SA");
                String password = urlProperties.getProperty("password", "");

                userManager.createFirstUser(username, password);
                schemaManager.createPublicSchema();
                logger.checkpoint(null, false, false);
            }

            lobManager.open();
            dbInfo.setWithContent(true);

            checkpointRunner = new CheckpointRunner();
            timeoutRunner    = new TimeoutRunner();
        } catch (Throwable e) {
            logger.close(Database.CLOSEMODE_IMMEDIATELY);
            logger.releaseLock();
            setState(DATABASE_SHUTDOWN);
            clearStructures();
            DatabaseManager.removeDatabase(this);

            if (!(e instanceof HsqlException)) {
                e = Error.error(ErrorCode.GENERAL_ERROR, e);
            }

            logger.logSevereEvent("could not reopen database", e);

            throw (HsqlException) e;
        }

        setState(DATABASE_ONLINE);
    }

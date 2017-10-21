    public void defrag(Session session) {

        database.logger.logInfoEvent("defrag start");

        try {

// test
/**/
/**/
/**/

//
            synchLog();
            database.lobManager.synch();
            deleteOldDataFiles();

            DataFileDefrag dfd = cache.defrag(session);

            database.persistentStoreCollection.setNewTableSpaces();
            database.schemaManager.setIndexRoots(dfd.getIndexRoots());
            database.sessionManager.resetLoggedSchemas();
        } catch (HsqlException e) {
            throw e;
        } catch (Throwable e) {
            database.logger.logSevereEvent("defrag failure", e);

            throw Error.error(ErrorCode.DATA_FILE_ERROR, e);
        }

// test
/**/
/**/
/**/

//
        database.logger.logInfoEvent("defrag end");
    }

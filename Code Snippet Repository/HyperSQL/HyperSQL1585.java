    DataFileDefrag defrag(Session session) {

        writeLock.lock();

        try {
            cache.saveAll();

            DataFileDefrag dfd = new DataFileDefrag(database, this);

            dfd.process(session);
            close();
            cache.clear();

            if (!database.logger.propIncrementBackup) {
                backupNewDataFile(true);
            }

            database.schemaManager.setTempIndexRoots(dfd.getIndexRoots());

            try {
                database.logger.log.writeScript(false);
            } finally {
                database.schemaManager.setTempIndexRoots(null);
            }

            database.getProperties().setProperty(
                HsqlDatabaseProperties.hsqldb_script_format,
                database.logger.propScriptFormat);
            database.getProperties().setDBModified(
                HsqlDatabaseProperties.FILES_MODIFIED_NEW);
            database.logger.log.closeLog();
            database.logger.log.deleteLog();
            database.logger.log.renameNewScript();
            renameBackupFile();
            renameDataFile();
            database.getProperties().setDBModified(
                HsqlDatabaseProperties.FILES_NOT_MODIFIED);
            open(false);

            if (database.logger.log.dbLogWriter != null) {
                database.logger.log.openLog();
            }

            return dfd;
        } finally {
            writeLock.unlock();
        }
    }

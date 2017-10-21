    public synchronized void resetSession() {

        if (isClosed) {
            return;
        }

        rollbackNoCheck(false);
        sessionData.closeAllNavigators();
        sessionData.persistentStoreCollection.clearAllTables();
        sessionData.clearLobOps();
        statementManager.reset();

        sessionContext.lastIdentity = ValuePool.INTEGER_0;
        sessionContext.isAutoCommit = Boolean.TRUE;

        setResultMemoryRowCount(database.getResultMaxMemoryRows());

        user = sessionUser;

        resetSchema();
        setZoneSeconds(sessionTimeZoneSeconds);

        sessionMaxRows = 0;
        ignoreCase     = database.sqlIgnoreCase;

        setIsolation(isolationLevelDefault);

        txConflictRollback = database.txConflictRollback;
    }

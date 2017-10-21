    public synchronized void close() {

        if (isClosed) {
            return;
        }

        rollback(false);

        try {
            database.logger.writeOtherStatement(this, Tokens.T_DISCONNECT);
        } catch (HsqlException e) {}

        sessionData.closeAllNavigators();
        sessionData.persistentStoreCollection.release();
        statementManager.reset();

        // keep sessionContext and sessionData
        rowActionList.clear();

        isClosed                    = true;
        user                        = null;
        sessionContext.savepoints   = null;
        sessionContext.lastIdentity = null;
        intConnection               = null;

        database.sessionManager.removeSession(this);
        database.closeIfLast();

        database = null;
    }

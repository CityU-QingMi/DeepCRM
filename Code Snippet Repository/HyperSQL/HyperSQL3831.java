    private void endTransaction(boolean commit, boolean chain) {

        abortTransaction = false;

        sessionContext.resetStack();
        sessionContext.savepoints.clear();
        sessionContext.savepointTimestamps.clear();
        rowActionList.clear();
        sessionData.persistentStoreCollection.clearTransactionTables();
        sessionData.closeAllTransactionNavigators();
        sessionData.clearLobOps();

        if (!chain) {
            sessionContext.isReadOnly = isReadOnlyDefault ? Boolean.TRUE
                                                          : Boolean.FALSE;

            setIsolation(isolationLevelDefault);
        }

        if (database.logger.getSqlEventLogLevel() > 0) {
            Statement endTX = commit ? StatementSession.commitNoChainStatement
                                     : StatementSession
                                         .rollbackNoChainStatement;

            database.logger.logStatementEvent(this, endTX, null,
                                              Result.updateZeroResult,
                                              SimpleLog.LOG_ERROR);
        }
/**/
/**/
/**/
/**/
    }

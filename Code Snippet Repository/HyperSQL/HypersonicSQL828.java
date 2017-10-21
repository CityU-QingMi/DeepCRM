    public synchronized void commit(boolean chain) {

//        tempActionHistory.add("commit " + actionTimestamp);
        if (isClosed) {
            return;
        }

        if (sessionContext.depth > 0) {
            return;
        }

        if (isTransaction) {
            if (!database.txManager.commitTransaction(this)) {

                // tempActionHistory.add("commit aborts " + actionTimestamp);
                rollbackNoCheck(chain);

                throw Error.error(ErrorCode.X_40001);
            }
        }

        endTransaction(true, chain);

        if (database != null && !sessionUser.isSystem()
                && database.logger.needsCheckpointReset()) {
            database.checkpointRunner.start();
        }
    }

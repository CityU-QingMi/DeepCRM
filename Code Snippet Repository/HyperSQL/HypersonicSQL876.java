    public synchronized void prepareCommit() {

        if (isClosed) {
            throw Error.error(ErrorCode.X_08003);
        }

        if (!database.txManager.prepareCommitActions(this)) {

//            tempActionHistory.add("commit aborts " + actionTimestamp);
            rollbackNoCheck(false);

            throw Error.error(ErrorCode.X_40001);
        }
    }

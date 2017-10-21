    public synchronized void rollbackToSavepoint(String name) {

        if (isClosed) {
            return;
        }

        int index = sessionContext.savepoints.getIndex(name);

        if (index < 0) {
            throw Error.error(ErrorCode.X_3B001, name);
        }

        database.txManager.rollbackSavepoint(this, index);
    }

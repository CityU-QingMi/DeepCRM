    public synchronized void setAutoCommit(boolean autocommit) {

        if (isClosed) {
            return;
        }

        if (sessionContext.depth > 0) {
            return;
        }

        if (sessionContext.isAutoCommit.booleanValue() != autocommit) {
            commit(false);

            sessionContext.isAutoCommit = autocommit ? Boolean.TRUE
                                                     : Boolean.FALSE;
        }
    }

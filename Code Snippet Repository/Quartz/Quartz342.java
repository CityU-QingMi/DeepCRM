    public void resumeAll()
        throws JobPersistenceException {
        executeInLock(
            LOCK_TRIGGER_ACCESS,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    resumeAll(conn);
                }
            });
    }

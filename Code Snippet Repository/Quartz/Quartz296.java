    public boolean removeJob(final JobKey jobKey) throws JobPersistenceException {
        return (Boolean) executeInLock(
                LOCK_TRIGGER_ACCESS,
                new TransactionCallback() {
                    public Object execute(Connection conn) throws JobPersistenceException {
                        return removeJob(conn, jobKey) ?
                                Boolean.TRUE : Boolean.FALSE;
                    }
                });
    }

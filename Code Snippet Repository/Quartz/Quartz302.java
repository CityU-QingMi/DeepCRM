    public boolean removeTrigger(final TriggerKey triggerKey) throws JobPersistenceException {
        return (Boolean) executeInLock(
                LOCK_TRIGGER_ACCESS,
                new TransactionCallback() {
                    public Object execute(Connection conn) throws JobPersistenceException {
                        return removeTrigger(conn, triggerKey) ?
                                Boolean.TRUE : Boolean.FALSE;
                    }
                });
    }

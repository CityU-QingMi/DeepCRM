    public boolean replaceTrigger(final TriggerKey triggerKey, 
            final OperableTrigger newTrigger) throws JobPersistenceException {
        return (Boolean) executeInLock(
                LOCK_TRIGGER_ACCESS,
                new TransactionCallback() {
                    public Object execute(Connection conn) throws JobPersistenceException {
                        return replaceTrigger(conn, triggerKey, newTrigger) ?
                                Boolean.TRUE : Boolean.FALSE;
                    }
                });
    }

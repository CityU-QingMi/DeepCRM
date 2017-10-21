    public boolean removeTriggers(final List<TriggerKey> triggerKeys)
            throws JobPersistenceException {
        return (Boolean) executeInLock(
                LOCK_TRIGGER_ACCESS,
                new TransactionCallback() {
                    public Object execute(Connection conn) throws JobPersistenceException {
                        boolean allFound = true;

                        // FUTURE_TODO: make this more efficient with a true bulk operation...
                        for (TriggerKey triggerKey : triggerKeys)
                            allFound = removeTrigger(conn, triggerKey) && allFound;

                        return allFound ? Boolean.TRUE : Boolean.FALSE;
                    }
                });
    }

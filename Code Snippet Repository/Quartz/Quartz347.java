    public void triggeredJobComplete(final OperableTrigger trigger,
            final JobDetail jobDetail, final CompletedExecutionInstruction triggerInstCode) {
        retryExecuteInNonManagedTXLock(
            LOCK_TRIGGER_ACCESS,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    triggeredJobComplete(conn, trigger, jobDetail,triggerInstCode);
                }
            });    
    }

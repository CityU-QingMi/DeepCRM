    public void resumeJob(final JobKey jobKey) throws JobPersistenceException {
        executeInLock(
            LOCK_TRIGGER_ACCESS,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    List<OperableTrigger> triggers = getTriggersForJob(conn, jobKey);
                    for (OperableTrigger trigger: triggers) {
                        resumeTrigger(conn, trigger.getKey());
                    }
                }
            });
    }

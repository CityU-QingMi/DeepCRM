    public void storeJobAndTrigger(final JobDetail newJob,
            final OperableTrigger newTrigger) 
        throws JobPersistenceException {
        executeInLock(
            (isLockOnInsert()) ? LOCK_TRIGGER_ACCESS : null,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    storeJob(conn, newJob, false);
                    storeTrigger(conn, newTrigger, newJob, false,
                            Constants.STATE_WAITING, false, false);
                }
            });
    }

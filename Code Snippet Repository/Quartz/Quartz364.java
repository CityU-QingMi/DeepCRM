    public void storeJob(final JobDetail newJob,
        final boolean replaceExisting) throws JobPersistenceException {
        executeInLock(
            (isLockOnInsert() || replaceExisting) ? LOCK_TRIGGER_ACCESS : null,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    storeJob(conn, newJob, replaceExisting);
                }
            });
    }

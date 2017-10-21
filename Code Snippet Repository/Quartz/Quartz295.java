    public void storeTrigger(final OperableTrigger newTrigger,
        final boolean replaceExisting) throws JobPersistenceException {
        executeInLock(
            (isLockOnInsert() || replaceExisting) ? LOCK_TRIGGER_ACCESS : null,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    storeTrigger(conn, newTrigger, null, replaceExisting,
                        STATE_WAITING, false, false);
                }
            });
    }

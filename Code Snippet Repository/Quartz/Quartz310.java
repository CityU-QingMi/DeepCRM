    public void storeCalendar(final String calName,
        final Calendar calendar, final boolean replaceExisting, final boolean updateTriggers)
        throws JobPersistenceException {
        executeInLock(
            (isLockOnInsert() || updateTriggers) ? LOCK_TRIGGER_ACCESS : null,
            new VoidTransactionCallback() {
                public void executeVoid(Connection conn) throws JobPersistenceException {
                    storeCalendar(conn, calName, calendar, replaceExisting, updateTriggers);
                }
            });
    }

    public boolean removeCalendar(final String calName)
        throws JobPersistenceException {
        return (Boolean) executeInLock(
                LOCK_TRIGGER_ACCESS,
                new TransactionCallback() {
                    public Object execute(Connection conn) throws JobPersistenceException {
                        return removeCalendar(conn, calName) ?
                                Boolean.TRUE : Boolean.FALSE;
                    }
                });
    }

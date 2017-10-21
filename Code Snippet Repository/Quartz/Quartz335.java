    @SuppressWarnings("")
    public Set<String> pauseTriggers(final GroupMatcher<TriggerKey> matcher)
        throws JobPersistenceException {
        return (Set<String>) executeInLock(
            LOCK_TRIGGER_ACCESS,
            new TransactionCallback() {
                public Set<String> execute(Connection conn) throws JobPersistenceException {
                    return pauseTriggerGroup(conn, matcher);
                }
            });
    }

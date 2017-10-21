    @SuppressWarnings("")
    public Set<TriggerKey> getTriggerKeys(final GroupMatcher<TriggerKey> matcher)
        throws JobPersistenceException {
        return (Set<TriggerKey>)executeWithoutLock( // no locks necessary for read...
            new TransactionCallback() {
                public Object execute(Connection conn) throws JobPersistenceException {
                    return getTriggerNames(conn, matcher);
                }
            });
    }

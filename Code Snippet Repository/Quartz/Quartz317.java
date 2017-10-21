    @SuppressWarnings("")
    public Set<JobKey> getJobKeys(final GroupMatcher<JobKey> matcher)
        throws JobPersistenceException {
        return (Set<JobKey>)executeWithoutLock( // no locks necessary for read...
            new TransactionCallback() {
                public Object execute(Connection conn) throws JobPersistenceException {
                    return getJobNames(conn, matcher);
                }
            });
    }

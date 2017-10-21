    @SuppressWarnings("")
    public List<String> getJobGroupNames()
        throws JobPersistenceException {
        return (List<String>)executeWithoutLock( // no locks necessary for read...
            new TransactionCallback() {
                public Object execute(Connection conn) throws JobPersistenceException {
                    return getJobGroupNames(conn);
                }
            });
    }

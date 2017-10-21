    @SuppressWarnings("")
    public List<String> getTriggerGroupNames()
        throws JobPersistenceException {
        return (List<String>)executeWithoutLock( // no locks necessary for read...
            new TransactionCallback() {
                public Object execute(Connection conn) throws JobPersistenceException {
                    return getTriggerGroupNames(conn);
                }
            });        
    }

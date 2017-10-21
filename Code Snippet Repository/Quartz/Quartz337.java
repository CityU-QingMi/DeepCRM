    @SuppressWarnings("")
    public Set<String> getPausedTriggerGroups() 
        throws JobPersistenceException {
        return (Set<String>)executeWithoutLock( // no locks necessary for read...
            new TransactionCallback() {
                public Object execute(Connection conn) throws JobPersistenceException {
                    return getPausedTriggerGroups(conn);
                }
            });
    }    

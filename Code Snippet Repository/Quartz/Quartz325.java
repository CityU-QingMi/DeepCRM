    @SuppressWarnings("")
    public List<String> getCalendarNames()
        throws JobPersistenceException {
        return (List<String>)executeWithoutLock( // no locks necessary for read...
            new TransactionCallback() {
                public Object execute(Connection conn) throws JobPersistenceException {
                    return getCalendarNames(conn);
                }
            });      
    }

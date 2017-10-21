    public boolean removeJobs(final List<JobKey> jobKeys) throws JobPersistenceException {

        return (Boolean) executeInLock(
                LOCK_TRIGGER_ACCESS,
                new TransactionCallback() {
                    public Object execute(Connection conn) throws JobPersistenceException {
                        boolean allFound = true;

                        // FUTURE_TODO: make this more efficient with a true bulk operation...
                        for (JobKey jobKey : jobKeys)
                            allFound = removeJob(conn, jobKey) && allFound;

                        return allFound ? Boolean.TRUE : Boolean.FALSE;
                    }
                });
    }

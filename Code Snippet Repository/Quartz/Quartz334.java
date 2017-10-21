    @SuppressWarnings("")
    public Set<String> resumeJobs(final GroupMatcher<JobKey> matcher)
        throws JobPersistenceException {
        return (Set<String>) executeInLock(
            LOCK_TRIGGER_ACCESS,
            new TransactionCallback() {
                public Set<String> execute(Connection conn) throws JobPersistenceException {
                    Set<JobKey> jobKeys = getJobNames(conn, matcher);
                    Set<String> groupNames = new HashSet<String>();

                    for (JobKey jobKey: jobKeys) {
                        List<OperableTrigger> triggers = getTriggersForJob(conn, jobKey);
                        for (OperableTrigger trigger: triggers) {
                            resumeTrigger(conn, trigger.getKey());
                        }
                        groupNames.add(jobKey.getGroup());
                    }
                    return groupNames;
                }
            });
    }

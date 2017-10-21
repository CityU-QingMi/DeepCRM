    @SuppressWarnings("")
    public Set<String> pauseJobs(final GroupMatcher<JobKey> matcher)
        throws JobPersistenceException {
        return (Set<String>) executeInLock(
            LOCK_TRIGGER_ACCESS,
            new TransactionCallback() {
                public Set<String> execute(final Connection conn) throws JobPersistenceException {
                    Set<String> groupNames = new HashSet<String>();
                    Set<JobKey> jobNames = getJobNames(conn, matcher);

                    for (JobKey jobKey : jobNames) {
                        List<OperableTrigger> triggers = getTriggersForJob(conn, jobKey);
                        for (OperableTrigger trigger : triggers) {
                            pauseTrigger(conn, trigger.getKey());
                        }
                        groupNames.add(jobKey.getGroup());
                    }

                    return groupNames;
                }
            }
            );
    }

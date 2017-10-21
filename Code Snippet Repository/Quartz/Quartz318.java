    protected Set<JobKey> getJobNames(Connection conn,
            GroupMatcher<JobKey> matcher) throws JobPersistenceException {
        Set<JobKey> jobNames;

        try {
            jobNames = getDelegate().selectJobsInGroup(conn, matcher);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't obtain job names: "
                    + e.getMessage(), e);
        }

        return jobNames;
    }

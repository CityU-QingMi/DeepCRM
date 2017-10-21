    protected boolean removeJob(Connection conn, final JobKey jobKey)
        throws JobPersistenceException {

        try {
            List<TriggerKey> jobTriggers = getDelegate().selectTriggerKeysForJob(conn, jobKey);
            for (TriggerKey jobTrigger: jobTriggers) {
                deleteTriggerAndChildren(conn, jobTrigger);
            }

            return deleteJobAndChildren(conn, jobKey);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't remove job: "
                    + e.getMessage(), e);
        }
    }

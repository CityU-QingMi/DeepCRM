    protected boolean removeTrigger(Connection conn, TriggerKey key)
        throws JobPersistenceException {
        boolean removedTrigger;
        try {
            // this must be called before we delete the trigger, obviously
            JobDetail job = getDelegate().selectJobForTrigger(conn,
                    getClassLoadHelper(), key, false);

            removedTrigger = 
                deleteTriggerAndChildren(conn, key);

            if (null != job && !job.isDurable()) {
                int numTriggers = getDelegate().selectNumTriggersForJob(conn,
                        job.getKey());
                if (numTriggers == 0) {
                    // Don't call removeJob() because we don't want to check for
                    // triggers again.
                    deleteJobAndChildren(conn, job.getKey());
                }
            }
        } catch (ClassNotFoundException e) {
            throw new JobPersistenceException("Couldn't remove trigger: "
                    + e.getMessage(), e);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't remove trigger: "
                    + e.getMessage(), e);
        }

        return removedTrigger;
    }

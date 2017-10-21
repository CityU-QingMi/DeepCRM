    protected boolean replaceTrigger(Connection conn, 
            TriggerKey key, OperableTrigger newTrigger)
        throws JobPersistenceException {
        try {
            // this must be called before we delete the trigger, obviously
            JobDetail job = getDelegate().selectJobForTrigger(conn,
                    getClassLoadHelper(), key);

            if (job == null) {
                return false;
            }
            
            if (!newTrigger.getJobKey().equals(job.getKey())) {
                throw new JobPersistenceException("New trigger is not related to the same job as the old trigger.");
            }
            
            boolean removedTrigger = 
                deleteTriggerAndChildren(conn, key);
            
            storeTrigger(conn, newTrigger, job, false, STATE_WAITING, false, false);

            return removedTrigger;
        } catch (ClassNotFoundException e) {
            throw new JobPersistenceException("Couldn't remove trigger: "
                    + e.getMessage(), e);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't remove trigger: "
                    + e.getMessage(), e);
        }
    }

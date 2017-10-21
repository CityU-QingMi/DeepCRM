    public void resumeTrigger(Connection conn, 
            TriggerKey key)
        throws JobPersistenceException {
        try {

            TriggerStatus status = getDelegate().selectTriggerStatus(conn,
                    key);

            if (status == null || status.getNextFireTime() == null) {
                return;
            }

            boolean blocked = false;
            if(STATE_PAUSED_BLOCKED.equals(status.getStatus())) {
                blocked = true;
            }

            String newState = checkBlockedState(conn, status.getJobKey(), STATE_WAITING);

            boolean misfired = false;

            if (schedulerRunning && status.getNextFireTime().before(new Date())) {
                misfired = updateMisfiredTrigger(conn, key,
                    newState, true);
            }

            if(!misfired) {
                if(blocked) {
                    getDelegate().updateTriggerStateFromOtherState(conn,
                            key, newState, STATE_PAUSED_BLOCKED);
                } else {
                    getDelegate().updateTriggerStateFromOtherState(conn,
                            key, newState, STATE_PAUSED);
                }
            } 

        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't resume trigger '"
                    + key + "': " + e.getMessage(), e);
        }
    }

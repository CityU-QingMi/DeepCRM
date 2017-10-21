    protected void releaseAcquiredTrigger(Connection conn,
            OperableTrigger trigger)
        throws JobPersistenceException {
        try {
            getDelegate().updateTriggerStateFromOtherState(conn,
                    trigger.getKey(), STATE_WAITING, STATE_ACQUIRED);
            getDelegate().deleteFiredTrigger(conn, trigger.getFireInstanceId());
        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't release acquired trigger: " + e.getMessage(), e);
        }
    }

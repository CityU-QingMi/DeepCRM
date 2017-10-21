    public void pauseTrigger(Connection conn, 
            TriggerKey triggerKey)
        throws JobPersistenceException {

        try {
            String oldState = getDelegate().selectTriggerState(conn,
                    triggerKey);

            if (oldState.equals(STATE_WAITING)
                    || oldState.equals(STATE_ACQUIRED)) {

                getDelegate().updateTriggerState(conn, triggerKey,
                        STATE_PAUSED);
            } else if (oldState.equals(STATE_BLOCKED)) {
                getDelegate().updateTriggerState(conn, triggerKey,
                        STATE_PAUSED_BLOCKED);
            }
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't pause trigger '"
                    + triggerKey + "': " + e.getMessage(), e);
        }
    }

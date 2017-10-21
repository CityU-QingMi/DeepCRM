    void resetTriggerFromErrorState(Connection conn, final TriggerKey triggerKey)
        throws JobPersistenceException {

        try {
            String newState = STATE_WAITING;

            if(getDelegate().isTriggerGroupPaused(conn, triggerKey.getGroup())) {
                newState = STATE_PAUSED;
            }

            getDelegate().updateTriggerStateFromOtherState(conn, triggerKey, newState, STATE_ERROR);

            getLog().info("Trigger " + triggerKey + " reset from ERROR state to: " + newState);
        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't reset from error state of trigger (" + triggerKey + "): " + e.getMessage(), e);
        }
    }

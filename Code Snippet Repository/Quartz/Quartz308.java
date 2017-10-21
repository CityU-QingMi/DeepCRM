    public TriggerState getTriggerState(Connection conn, TriggerKey key)
        throws JobPersistenceException {
        try {
            String ts = getDelegate().selectTriggerState(conn, key);

            if (ts == null) {
                return TriggerState.NONE;
            }

            if (ts.equals(STATE_DELETED)) {
                return TriggerState.NONE;
            }

            if (ts.equals(STATE_COMPLETE)) {
                return TriggerState.COMPLETE;
            }

            if (ts.equals(STATE_PAUSED)) {
                return TriggerState.PAUSED;
            }

            if (ts.equals(STATE_PAUSED_BLOCKED)) {
                return TriggerState.PAUSED;
            }

            if (ts.equals(STATE_ERROR)) {
                return TriggerState.ERROR;
            }

            if (ts.equals(STATE_BLOCKED)) {
                return TriggerState.BLOCKED;
            }

            return TriggerState.NORMAL;

        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't determine state of trigger (" + key + "): " + e.getMessage(), e);
        }
    }

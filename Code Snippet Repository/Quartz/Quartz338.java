    protected boolean updateMisfiredTrigger(Connection conn,
            TriggerKey triggerKey, String newStateIfNotComplete, boolean forceState)
        throws JobPersistenceException {
        try {

            OperableTrigger trig = retrieveTrigger(conn, triggerKey);

            long misfireTime = System.currentTimeMillis();
            if (getMisfireThreshold() > 0) {
                misfireTime -= getMisfireThreshold();
            }

            if (trig.getNextFireTime().getTime() > misfireTime) {
                return false;
            }

            doUpdateOfMisfiredTrigger(conn, trig, forceState, newStateIfNotComplete, false);

            return true;

        } catch (Exception e) {
            throw new JobPersistenceException(
                    "Couldn't update misfired trigger '" + triggerKey + "': " + e.getMessage(), e);
        }
    }

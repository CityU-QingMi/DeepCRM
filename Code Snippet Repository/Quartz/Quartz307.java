    protected OperableTrigger retrieveTrigger(Connection conn, TriggerKey key)
        throws JobPersistenceException {
        try {

            return getDelegate().selectTrigger(conn, key);
        } catch (Exception e) {
            throw new JobPersistenceException("Couldn't retrieve trigger: "
                    + e.getMessage(), e);
        }
    }

    protected Set<TriggerKey> getTriggerNames(Connection conn,
            GroupMatcher<TriggerKey> matcher) throws JobPersistenceException {

        Set<TriggerKey> trigNames;

        try {
            trigNames = getDelegate().selectTriggersInGroup(conn, matcher);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't obtain trigger names: "
                    + e.getMessage(), e);
        }

        return trigNames;
    }

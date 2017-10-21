    public void resumeAll(Connection conn)
        throws JobPersistenceException {

        List<String> names = getTriggerGroupNames(conn);

        for (String name: names) {
            resumeTriggerGroup(conn, GroupMatcher.triggerGroupEquals(name));
        }

        try {
            getDelegate().deletePausedTriggerGroup(conn, ALL_GROUPS_PAUSED);
        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't resume all trigger groups: " + e.getMessage(), e);
        }
    }

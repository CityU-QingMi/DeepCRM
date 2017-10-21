    public void pauseAll(Connection conn)
        throws JobPersistenceException {

        List<String> names = getTriggerGroupNames(conn);

        for (String name: names) {
            pauseTriggerGroup(conn, GroupMatcher.triggerGroupEquals(name));
        }

        try {
            if (!getDelegate().isTriggerGroupPaused(conn, ALL_GROUPS_PAUSED)) {
                getDelegate().insertPausedTriggerGroup(conn, ALL_GROUPS_PAUSED);
            }

        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't pause all trigger groups: " + e.getMessage(), e);
        }

    }

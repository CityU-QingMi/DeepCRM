    protected List<String> getTriggerGroupNames(Connection conn) throws JobPersistenceException {

        List<String> groupNames;

        try {
            groupNames = getDelegate().selectTriggerGroups(conn);
        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't obtain trigger groups: " + e.getMessage(), e);
        }

        return groupNames;
    }

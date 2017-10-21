    protected List<String> getJobGroupNames(Connection conn)
        throws JobPersistenceException {

        List<String> groupNames;

        try {
            groupNames = getDelegate().selectJobGroups(conn);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't obtain job groups: "
                    + e.getMessage(), e);
        }

        return groupNames;
    }

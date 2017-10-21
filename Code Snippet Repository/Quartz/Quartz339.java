    public Set<String> getPausedTriggerGroups(Connection conn) 
        throws JobPersistenceException {

        try {
            return getDelegate().selectPausedTriggerGroups(conn);
        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't determine paused trigger groups: " + e.getMessage(), e);
        }
    }

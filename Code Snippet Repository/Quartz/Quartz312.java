    protected boolean calendarExists(Connection conn, String calName)
        throws JobPersistenceException {
        try {
            return getDelegate().calendarExists(conn, calName);
        } catch (SQLException e) {
            throw new JobPersistenceException(
                    "Couldn't determine calendar existence (" + calName + "): "
                            + e.getMessage(), e);
        }
    }

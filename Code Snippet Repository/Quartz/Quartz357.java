    protected void commitConnection(Connection conn)
        throws JobPersistenceException {

        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new JobPersistenceException(
                    "Couldn't commit jdbc connection. "+e.getMessage(), e);
            }
        }
    }

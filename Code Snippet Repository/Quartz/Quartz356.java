    protected void rollbackConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                getLog().error(
                    "Couldn't rollback jdbc connection. "+e.getMessage(), e);
            }
        }
    }

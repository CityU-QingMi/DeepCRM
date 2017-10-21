    protected static String getUniqueNameFor(Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery("CALL database_name()");
            if (!rs.next()) {
                throw new SQLException(
                        "Engine did not reveal unique database name");
            }
            return rs.getString(1);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException se) {
                logger.error(
                        "Failed to close ResultSet for retrieving db name");
            }
            rs = null;  // Encourage GC
            try {
                st.close();
            } catch (SQLException se) {
                logger.error(
                        "Failed to close Statement for retrieving db name");
            }
            st = null;  // Encourage GC
        }
    }

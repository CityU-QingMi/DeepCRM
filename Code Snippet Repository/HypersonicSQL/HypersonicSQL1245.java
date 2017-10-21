    static String getInitialSchema(Connection c) throws SQLException {
        Statement st = c.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(
                "SELECT initial_schema FROM information_schema.system_users\n"
                + "WHERE user_name = current_user");
            if (!rs.next()) {
                throw new IllegalStateException(
                        "Failed to retrieve initial_schema for current user");
            }
            return rs.getString(1);
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException se) {
                logger.error("Failed "
                        + "to close ResultSet for retrieving initial schema");
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

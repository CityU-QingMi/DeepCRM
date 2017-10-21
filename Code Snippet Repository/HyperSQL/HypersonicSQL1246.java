    static Set getEnabledRoles(Connection c) throws SQLException {
        Set roles = new HashSet<String>();
        Statement st = c.createStatement();
        ResultSet rs = null;
        try {
            rs = st.executeQuery(
                    "SELECT * FROM information_schema.enabled_roles");
            while (rs.next()) roles.add(rs.getString(1));
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
        return roles;
    }

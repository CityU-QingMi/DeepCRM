    public List<String> selectJobGroups(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(rtp(SELECT_JOB_GROUPS));
            rs = ps.executeQuery();

            LinkedList<String> list = new LinkedList<String>();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            return list;
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
        }
    }

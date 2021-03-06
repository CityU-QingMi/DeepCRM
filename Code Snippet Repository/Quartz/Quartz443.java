    public List<String> selectCalendars(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(rtp(SELECT_CALENDARS));
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

    public int deleteFiredTriggers(Connection conn, String theInstanceId)
        throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(rtp(DELETE_INSTANCES_FIRED_TRIGGERS));
            ps.setString(1, theInstanceId);

            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

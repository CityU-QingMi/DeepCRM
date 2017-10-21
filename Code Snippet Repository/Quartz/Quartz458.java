    public int updateSchedulerState(Connection conn, String theInstanceId, long checkInTime)
        throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(rtp(UPDATE_SCHEDULER_STATE));
            ps.setLong(1, checkInTime);
            ps.setString(2, theInstanceId);
        
            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

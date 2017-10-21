    public int deletePausedTriggerGroup(Connection conn, GroupMatcher<TriggerKey> matcher)
        throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(rtp(DELETE_PAUSED_TRIGGER_GROUP));
            ps.setString(1, toSqlLikeClause(matcher));
            int rows = ps.executeUpdate();

            return rows;
        } finally {
            closeStatement(ps);
        }
    }

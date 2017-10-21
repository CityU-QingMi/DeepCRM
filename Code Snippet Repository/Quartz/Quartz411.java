    public int updateTriggerGroupStateFromOtherState(Connection conn,
            GroupMatcher<TriggerKey> matcher, String newState, String oldState)
        throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = conn
                    .prepareStatement(rtp(UPDATE_TRIGGER_GROUP_STATE_FROM_STATE));
            ps.setString(1, newState);
            ps.setString(2, toSqlLikeClause(matcher));
            ps.setString(3, oldState);

            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

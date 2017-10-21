    public Set<TriggerKey> selectTriggersInGroup(Connection conn, GroupMatcher<TriggerKey> matcher)
        throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if(isMatcherEquals(matcher)) {
                ps = conn.prepareStatement(rtp(SELECT_TRIGGERS_IN_GROUP));
                ps.setString(1, toSqlEqualsClause(matcher));
            }
            else {
                ps = conn.prepareStatement(rtp(SELECT_TRIGGERS_IN_GROUP_LIKE));
                ps.setString(1, toSqlLikeClause(matcher));
            }
            rs = ps.executeQuery();

            Set<TriggerKey> keys = new HashSet<TriggerKey>();
            while (rs.next()) {
                keys.add(triggerKey(rs.getString(1), rs.getString(2)));
            }

            return keys;
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
        }
    }

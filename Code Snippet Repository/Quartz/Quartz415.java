    public int deleteTrigger(Connection conn, TriggerKey triggerKey) throws SQLException {
        PreparedStatement ps = null;

        deleteTriggerExtension(conn, triggerKey);
        
        try {
            ps = conn.prepareStatement(rtp(DELETE_TRIGGER));
            ps.setString(1, triggerKey.getName());
            ps.setString(2, triggerKey.getGroup());

            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

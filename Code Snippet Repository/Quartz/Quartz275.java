    public int deleteExtendedTriggerProperties(Connection conn, TriggerKey triggerKey) throws SQLException {

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(Util.rtp(DELETE_CRON_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, triggerKey.getName());
            ps.setString(2, triggerKey.getGroup());

            return ps.executeUpdate();
        } finally {
            Util.closeStatement(ps);
        }
    }

    public int updateExtendedTriggerProperties(Connection conn, OperableTrigger trigger, String state, JobDetail jobDetail) throws SQLException, IOException {

        SimpleTrigger simpleTrigger = (SimpleTrigger)trigger;
        
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(Util.rtp(UPDATE_SIMPLE_TRIGGER, tablePrefix, schedNameLiteral));

            ps.setInt(1, simpleTrigger.getRepeatCount());
            ps.setBigDecimal(2, new BigDecimal(String.valueOf(simpleTrigger.getRepeatInterval())));
            ps.setInt(3, simpleTrigger.getTimesTriggered());
            ps.setString(4, simpleTrigger.getKey().getName());
            ps.setString(5, simpleTrigger.getKey().getGroup());

            return ps.executeUpdate();
        } finally {
            Util.closeStatement(ps);
        }
    }

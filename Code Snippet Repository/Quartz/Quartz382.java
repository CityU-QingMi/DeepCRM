    public int insertExtendedTriggerProperties(Connection conn, OperableTrigger trigger, String state, JobDetail jobDetail) throws SQLException, IOException {

        SimpleTrigger simpleTrigger = (SimpleTrigger)trigger;
        
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(Util.rtp(INSERT_SIMPLE_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, trigger.getKey().getName());
            ps.setString(2, trigger.getKey().getGroup());
            ps.setInt(3, simpleTrigger.getRepeatCount());
            ps.setBigDecimal(4, new BigDecimal(String.valueOf(simpleTrigger.getRepeatInterval())));
            ps.setInt(5, simpleTrigger.getTimesTriggered());

            return ps.executeUpdate();
        } finally {
            Util.closeStatement(ps);
        }
    }

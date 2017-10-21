    public int updateExtendedTriggerProperties(Connection conn, OperableTrigger trigger, String state, JobDetail jobDetail) throws SQLException, IOException {

        SimplePropertiesTriggerProperties properties = getTriggerProperties(trigger);
        
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(Util.rtp(UPDATE_SIMPLE_PROPS_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, properties.getString1());
            ps.setString(2, properties.getString2());
            ps.setString(3, properties.getString3());
            ps.setInt(4, properties.getInt1());
            ps.setInt(5, properties.getInt2());
            ps.setLong(6, properties.getLong1());
            ps.setLong(7, properties.getLong2());
            ps.setBigDecimal(8, properties.getDecimal1());
            ps.setBigDecimal(9, properties.getDecimal2());
            ps.setBoolean(10, properties.isBoolean1());
            ps.setBoolean(11, properties.isBoolean2());
            ps.setString(12, trigger.getKey().getName());
            ps.setString(13, trigger.getKey().getGroup());

            return ps.executeUpdate();
        } finally {
            Util.closeStatement(ps);
        }
    }

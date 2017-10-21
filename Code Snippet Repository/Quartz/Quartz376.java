    public int insertExtendedTriggerProperties(Connection conn, OperableTrigger trigger, String state, JobDetail jobDetail) throws SQLException, IOException {

        SimplePropertiesTriggerProperties properties = getTriggerProperties(trigger);
        
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(Util.rtp(INSERT_SIMPLE_PROPS_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, trigger.getKey().getName());
            ps.setString(2, trigger.getKey().getGroup());
            ps.setString(3, properties.getString1());
            ps.setString(4, properties.getString2());
            ps.setString(5, properties.getString3());
            ps.setInt(6, properties.getInt1());
            ps.setInt(7, properties.getInt2());
            ps.setLong(8, properties.getLong1());
            ps.setLong(9, properties.getLong2());
            ps.setBigDecimal(10, properties.getDecimal1());
            ps.setBigDecimal(11, properties.getDecimal2());
            ps.setBoolean(12, properties.isBoolean1());
            ps.setBoolean(13, properties.isBoolean2());

            return ps.executeUpdate();
        } finally {
            Util.closeStatement(ps);
        }
    }

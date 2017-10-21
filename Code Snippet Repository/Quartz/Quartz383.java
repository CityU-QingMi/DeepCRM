    public TriggerPropertyBundle loadExtendedTriggerProperties(Connection conn, TriggerKey triggerKey) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(Util.rtp(SELECT_SIMPLE_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, triggerKey.getName());
            ps.setString(2, triggerKey.getGroup());
            rs = ps.executeQuery();
    
            if (rs.next()) {
                int repeatCount = rs.getInt(COL_REPEAT_COUNT);
                long repeatInterval = rs.getLong(COL_REPEAT_INTERVAL);
                int timesTriggered = rs.getInt(COL_TIMES_TRIGGERED);

                SimpleScheduleBuilder sb = SimpleScheduleBuilder.simpleSchedule()
                    .withRepeatCount(repeatCount)
                    .withIntervalInMilliseconds(repeatInterval);
                
                String[] statePropertyNames = { "timesTriggered" };
                Object[] statePropertyValues = { timesTriggered };
                
                return new TriggerPropertyBundle(sb, statePropertyNames, statePropertyValues);
            }
            
            throw new IllegalStateException("No record found for selection of Trigger with key: '" + triggerKey + "' and statement: " + Util.rtp(SELECT_SIMPLE_TRIGGER, tablePrefix, schedNameLiteral));
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(ps);
        }
    }

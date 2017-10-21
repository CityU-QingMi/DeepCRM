    public TriggerPropertyBundle loadExtendedTriggerProperties(Connection conn, TriggerKey triggerKey) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(Util.rtp(SELECT_CRON_TRIGGER, tablePrefix, schedNameLiteral));
            ps.setString(1, triggerKey.getName());
            ps.setString(2, triggerKey.getGroup());
            rs = ps.executeQuery();

            if (rs.next()) {
                String cronExpr = rs.getString(COL_CRON_EXPRESSION);
                String timeZoneId = rs.getString(COL_TIME_ZONE_ID);

                CronScheduleBuilder cb = CronScheduleBuilder.cronSchedule(cronExpr);
              
                if (timeZoneId != null) 
                    cb.inTimeZone(TimeZone.getTimeZone(timeZoneId));
                
                return new TriggerPropertyBundle(cb, null, null);
            }
            
            throw new IllegalStateException("No record found for selection of Trigger with key: '" + triggerKey + "' and statement: " + Util.rtp(SELECT_CRON_TRIGGER, tablePrefix, schedNameLiteral));
        } finally {
            Util.closeResultSet(rs);
            Util.closeStatement(ps);
        }
    }

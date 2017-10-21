    public int updateFiredTrigger(Connection conn, OperableTrigger trigger,
            String state, JobDetail job) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(rtp(UPDATE_FIRED_TRIGGER));
            
            ps.setString(1, instanceId);

            ps.setBigDecimal(2, new BigDecimal(String.valueOf(System.currentTimeMillis())));
            ps.setBigDecimal(3, new BigDecimal(String.valueOf(trigger.getNextFireTime().getTime())));
            ps.setString(4, state);

            if (job != null) {
                ps.setString(5, trigger.getJobKey().getName());
                ps.setString(6, trigger.getJobKey().getGroup());
                setBoolean(ps, 7, job.isConcurrentExectionDisallowed());
                setBoolean(ps, 8, job.requestsRecovery());
            } else {
                ps.setString(5, null);
                ps.setString(6, null);
                setBoolean(ps, 7, false);
                setBoolean(ps, 8, false);
            }

            ps.setString(9, trigger.getFireInstanceId());


            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

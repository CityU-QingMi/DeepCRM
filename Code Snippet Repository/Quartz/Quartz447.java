    public int insertFiredTrigger(Connection conn, OperableTrigger trigger,
            String state, JobDetail job) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(rtp(INSERT_FIRED_TRIGGER));
            ps.setString(1, trigger.getFireInstanceId());
            ps.setString(2, trigger.getKey().getName());
            ps.setString(3, trigger.getKey().getGroup());
            ps.setString(4, instanceId);
            ps.setBigDecimal(5, new BigDecimal(String.valueOf(System.currentTimeMillis())));
            ps.setBigDecimal(6, new BigDecimal(String.valueOf(trigger.getNextFireTime().getTime())));
            ps.setString(7, state);
            if (job != null) {
                ps.setString(8, trigger.getJobKey().getName());
                ps.setString(9, trigger.getJobKey().getGroup());
                setBoolean(ps, 10, job.isConcurrentExectionDisallowed());
                setBoolean(ps, 11, job.requestsRecovery());
            } else {
                ps.setString(8, null);
                ps.setString(9, null);
                setBoolean(ps, 10, false);
                setBoolean(ps, 11, false);
            }
            ps.setInt(12, trigger.getPriority());

            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

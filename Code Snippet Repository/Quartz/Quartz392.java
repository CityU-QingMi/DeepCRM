    public int deleteJobDetail(Connection conn, JobKey jobKey)
        throws SQLException {
        PreparedStatement ps = null;

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Deleting job: " + jobKey);
            }
            ps = conn.prepareStatement(rtp(DELETE_JOB_DETAIL));
            ps.setString(1, jobKey.getName());
            ps.setString(2, jobKey.getGroup());
            return ps.executeUpdate();
        } finally {
            closeStatement(ps);
        }
    }

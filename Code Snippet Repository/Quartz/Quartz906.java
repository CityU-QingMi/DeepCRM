    @Override
    public int updateJobData(Connection conn, JobDetail job)
        throws IOException, SQLException {
        
        ByteArrayOutputStream baos = serializeJobData(job.getJobDataMap());
        byte[] data = baos.toByteArray();

        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(rtp(SELECT_ORACLE_JOB_DETAIL_BLOB));
            ps.setString(1, job.getKey().getName());
            ps.setString(2, job.getKey().getGroup());

            rs = ps.executeQuery();

            int res = 0;

            if (rs.next()) {
                Blob dbBlob = writeDataToBlob(rs, 1, data);
                ps2 = conn.prepareStatement(rtp(UPDATE_ORACLE_JOB_DETAIL_BLOB));

                ps2.setBlob(1, dbBlob);
                ps2.setString(2, job.getKey().getName());
                ps2.setString(3, job.getKey().getGroup());

                res = ps2.executeUpdate();
            }

            return res;
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
            closeStatement(ps2);
        }
    }

    private boolean lockViaUpdate(Connection conn, String lockName, String sql) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        try {
            ps.setString(1, lockName);
            getLog().debug("Lock '" + lockName + "' is being obtained: " + Thread.currentThread().getName());
            return ps.executeUpdate() >= 1;
        } finally {
            ps.close();
        }
    }

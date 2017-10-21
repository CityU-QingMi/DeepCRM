    public void doJdbcWork() throws SQLException {
        if (!initialized)
            throw new IllegalStateException(JdbcAppClass.class.getName()
                    + " instance not initialized");
        Connection c = null;
        ResultSet rs = null;
        try {
            c = ds.getConnection();
            rs = c.createStatement().executeQuery("SELECT * FROM t1");
            if (!rs.next()) {
                log.error("App class failed to retrieve data from catalog");
                return;
            }
            if (rs.getInt(1) != 456) {
                log.error("App class retrieved wrong value: "  + rs.getInt(1));
                return;
            }
            if (rs.next()) {
                log.error("App class failed too much data from catalog");
                return;
            }
        } finally {
            if (c != null) try {
                c.rollback();
            } catch (SQLException se) {
                // Intentionally empty.
                // We have done nothing that we want to commit, but want to
                // aggressively free transactional resources.
            }
            if (rs != null) try {
                rs.close();
            } catch (SQLException se) {
                log.error("Failed to close emulation database setup Connection",
                        se);
            } finally {
                rs = null;  // Encourage GC
            }
            if (c != null) try {
                c.close();
            } catch (SQLException se) {
                log.error("Failed to close emulation database setup Connection",
                        se);
            } finally {
                c = null;  // Encourage GC
            }
        }
        log.info("Application Success");
    }

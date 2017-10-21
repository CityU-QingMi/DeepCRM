    public void init() throws SQLException {
        if (masterJdbcUrl == null) {
            throw new IllegalStateException(
                    "Required property 'masterJdbcUrl' not set");
        }
        if (validationUser != null || validationPassword != null) {
            if (validationUser == null || validationPassword == null) {
                throw new IllegalStateException(
                        "If you set one property of 'validationUser' or "
                        + "'validationPassword', then you must set both.");
            }
            Connection c = null;
            SQLException problem = null;
            try {
                c = DriverManager.getConnection(
                        masterJdbcUrl, validationUser, validationPassword);
            } catch (SQLException se) {
                logger.error("Master/slave Connection validation failure", se);
                problem = se;  // Just indicates to let the original exception
                  // percolate through in the finally block, to prevent an
                  // exception in the finally block from obscuring the ultimate
                  // cause of the problem.
            } finally {
                if (c != null) try {
                    c.close();
                    c = null;  // Encourage GC
                } catch (SQLException nestedSe) {
                    logger.error(
                            "Failed to close test master/slave Connection",
                            nestedSe);
                    if (problem == null) {
                        throw nestedSe;
                    }
                }
            }
        }
        initialized = true;
    }

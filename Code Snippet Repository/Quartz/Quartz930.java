    public void initialize() throws SQLException {
        try {
            driver = (Driver) weblogic.jdbc.jts.Driver.class.newInstance();
        } catch (Exception e) {
            throw new SQLException(
                    "Could not get weblogic pool connection with name '"
                            + poolName + "': " + e.getClass().getName() + ": "
                            + e.getMessage());
        }
    }

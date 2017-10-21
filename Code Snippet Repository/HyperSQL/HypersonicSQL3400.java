        public void connect() throws SQLException {
            if (conn != null) {
                throw new IllegalStateException("Thread '" + getName()
                        + "' has already been connected");
            }
            try {
                conn = rcdata.getConnection();
            } catch (Exception e) {
                throw new RuntimeException(
                        "Failed to connect to get JDBC connection for '"
                        + getName() + "'", e);
            }
            conn.setAutoCommit(false);
            System.out.println("ScriptRun '" + getName() + "' connected with "
                    + RCData.tiToString(conn.getTransactionIsolation()) + '.');
        }

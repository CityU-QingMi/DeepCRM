    public synchronized Statement createStatement() throws SQLException {

        checkClosed();

        int props =
            ResultProperties.getValueForJDBC(JDBCResultSet.TYPE_FORWARD_ONLY,
                JDBCResultSet.CONCUR_READ_ONLY, rsHoldability);
        Statement stmt = new JDBCStatement(this, props);

        return stmt;
    }

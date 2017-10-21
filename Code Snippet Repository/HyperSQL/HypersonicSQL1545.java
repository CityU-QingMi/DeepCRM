    public synchronized Statement createStatement(int resultSetType,
            int resultSetConcurrency) throws SQLException {

        checkClosed();

        int props = ResultProperties.getValueForJDBC(resultSetType,
            resultSetConcurrency, rsHoldability);

        return new JDBCStatement(this, props);
    }

    public synchronized Statement createStatement(int resultSetType,
            int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {

        checkClosed();

        int props = ResultProperties.getValueForJDBC(resultSetType,
            resultSetConcurrency, resultSetHoldability);

        return new JDBCStatement(this, props);
    }

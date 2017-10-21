    public synchronized CallableStatement prepareCall(String sql,
            int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {

        checkClosed();

        try {
            return new JDBCCallableStatement(this, sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

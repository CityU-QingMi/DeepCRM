    public synchronized CallableStatement prepareCall(String sql,
            int resultSetType, int resultSetConcurrency) throws SQLException {

        checkClosed();

        try {
            return new JDBCCallableStatement(this, sql, resultSetType,
                    resultSetConcurrency, rsHoldability);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

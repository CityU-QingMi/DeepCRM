    public synchronized PreparedStatement prepareStatement(String sql,
            int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {

        checkClosed();

        try {
            return new JDBCPreparedStatement(this, sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability,
                    ResultConstants.RETURN_NO_GENERATED_KEYS, null, null);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

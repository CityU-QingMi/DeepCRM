    public synchronized PreparedStatement prepareStatement(
            String sql) throws SQLException {

        checkClosed();

        try {
            return new JDBCPreparedStatement(this, sql,
                    JDBCResultSet.TYPE_FORWARD_ONLY,
                    JDBCResultSet.CONCUR_READ_ONLY, rsHoldability,
                    ResultConstants.RETURN_NO_GENERATED_KEYS, null, null);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

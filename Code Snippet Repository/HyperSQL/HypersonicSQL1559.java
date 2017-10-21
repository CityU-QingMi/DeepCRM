    public synchronized PreparedStatement prepareStatement(String sql,
            String[] columnNames) throws SQLException {

        checkClosed();

        try {
            return new JDBCPreparedStatement(this, sql,
                    JDBCResultSet.TYPE_FORWARD_ONLY,
                    JDBCResultSet.CONCUR_READ_ONLY, rsHoldability,
                    ResultConstants.RETURN_GENERATED_KEYS_COL_NAMES, null,
                    columnNames);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

    public synchronized PreparedStatement prepareStatement(String sql,
            int[] columnIndexes) throws SQLException {

        checkClosed();

        try {
            return new JDBCPreparedStatement(this, sql,
                    JDBCResultSet.TYPE_FORWARD_ONLY,
                    JDBCResultSet.CONCUR_READ_ONLY, rsHoldability,
                    ResultConstants.RETURN_GENERATED_KEYS_COL_INDEXES,
                    columnIndexes, null);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

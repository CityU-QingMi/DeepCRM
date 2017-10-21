    public synchronized CallableStatement prepareCall(
            String sql) throws SQLException {

        CallableStatement stmt;

        checkClosed();

        try {
            stmt = new JDBCCallableStatement(this, sql,
                    JDBCResultSet.TYPE_FORWARD_ONLY,
                    JDBCResultSet.CONCUR_READ_ONLY, rsHoldability);

            return stmt;
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }

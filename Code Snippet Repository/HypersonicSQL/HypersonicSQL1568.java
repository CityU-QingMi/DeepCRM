    public void reset() throws SQLException {

        try {
            incarnation++;

            this.sessionProxy.resetSession();
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(ErrorCode.X_08006, e.getMessage(), e);
        }
    }

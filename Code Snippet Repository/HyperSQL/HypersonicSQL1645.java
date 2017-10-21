    protected void checkStatementType(int type) throws SQLException {

        if (type != statementRetType) {
            if (statementRetType == StatementTypes.RETURN_COUNT) {
                throw JDBCUtil.sqlException(ErrorCode.X_07504);
            } else {
                throw JDBCUtil.sqlException(ErrorCode.X_07503);
            }
        }
    }

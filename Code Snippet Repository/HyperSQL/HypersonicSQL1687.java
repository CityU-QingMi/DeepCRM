    protected Object[] getCurrent() throws SQLException {

        final RowSetNavigator lnavigator = this.navigator;

        if (lnavigator == null) {
            throw JDBCUtil.sqlException(ErrorCode.X_24501);
        } else if (lnavigator.isEmpty()) {
            throw JDBCUtil.sqlException(ErrorCode.X_24504, ErrorCode.M_RS_EMPTY);
        } else if (lnavigator.isBeforeFirst()) {
            throw JDBCUtil.sqlException(ErrorCode.X_24504,
                                    ErrorCode.M_RS_BEFORE_FIRST);
        } else if (lnavigator.isAfterLast()) {
            throw JDBCUtil.sqlException(ErrorCode.X_24504,
                                    ErrorCode.M_RS_AFTER_LAST);
        }

        Object[] data = lnavigator.getCurrent();

        if (data == null) {
            throw JDBCUtil.sqlException(ErrorCode.X_24501);
        }

        return data;
    }

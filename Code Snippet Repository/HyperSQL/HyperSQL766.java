    protected void checkColumn(int columnIndex) throws SQLException {

        if (navigator == null) {
            throw JDBCUtil.sqlException(ErrorCode.X_24501);
        }

        if (columnIndex < 1 || columnIndex > columnCount) {
            throw JDBCUtil.sqlException(ErrorCode.JDBC_COLUMN_NOT_FOUND,
                                    String.valueOf(columnIndex));
        }
    }

    public synchronized ResultSet executeQuery() throws SQLException {

        fetchResult();

        ResultSet rs = getResultSet();

        if (rs != null) {
            return rs;
        }

        if (getMoreResults()) {
            return getResultSet();
        }

        throw JDBCUtil.sqlException(ErrorCode.X_07504);
    }

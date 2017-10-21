    public ResultSetMetaData getMetaData() throws SQLException {

        checkClosed();

        if (resultSetMetaData == null) {
            resultSetMetaData = new JDBCResultSetMetaData(resultMetaData,
                    isUpdatable, isInsertable, connection);
        }

        return resultSetMetaData;
    }

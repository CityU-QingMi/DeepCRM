    public synchronized ResultSetMetaData getMetaData() throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (statementRetType != StatementTypes.RETURN_RESULT) {
            return null;
        }

        if (resultSetMetaData == null) {
            boolean isUpdatable  = ResultProperties.isUpdatable(rsProperties);
            boolean isInsertable = isUpdatable;

            if (isInsertable) {
                for (int i = 0; i < resultMetaData.colIndexes.length; i++) {
                    if (resultMetaData.colIndexes[i] < 0) {
                        isInsertable = false;

                        break;
                    }
                }
            }
            resultSetMetaData = new JDBCResultSetMetaData(resultMetaData,
                    isUpdatable, isInsertable, connection);
        }

        return resultSetMetaData;
    }

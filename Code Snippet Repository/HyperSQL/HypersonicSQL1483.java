    int findParameterIndex(String parameterName) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (parameterName == null) {
            throw JDBCUtil.nullArgument();
        }

        int index = parameterNameMap.get(parameterName, -1);

        if (index >= 0) {
            return index + 1;
        }

        index = parameterNameMap.get(parameterName.toUpperCase(Locale.ENGLISH),
                                     -1);

        if (index >= 0) {
            return index + 1;
        }

        throw JDBCUtil.sqlException(ErrorCode.JDBC_COLUMN_NOT_FOUND,
                                parameterName);
    }

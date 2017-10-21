    public synchronized Clob getClob(int parameterIndex) throws SQLException {

        checkGetParameterIndex(parameterIndex);

        Type   sourceType = parameterMetaData.columnTypes[parameterIndex - 1];
        Object o          = getColumnInType(parameterIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof ClobDataID) {
            return new JDBCClobClient(session, (ClobDataID) o);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }

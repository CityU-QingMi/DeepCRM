    public Reader getCharacterStream(int parameterIndex) throws SQLException {

        checkGetParameterIndex(parameterIndex);

        Type   sourceType = parameterMetaData.columnTypes[parameterIndex - 1];
        Object o          = getColumnInType(parameterIndex, sourceType);

        if (o == null) {
            return null;
        }

        if (o instanceof ClobDataID) {
            return ((ClobDataID) o).getCharacterStream(session);
        } else if (o instanceof Clob) {
            return ((Clob) o).getCharacterStream();
        } else if (o instanceof String) {
            return new StringReader((String) o);
        }

        throw JDBCUtil.sqlException(ErrorCode.X_42561);
    }

    public Array getArray(int parameterIndex) throws SQLException {

        checkGetParameterIndex(parameterIndex);

        Type type = parameterMetaData.columnTypes[parameterIndex - 1];

        if (!type.isArrayType()) {
            throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }

        Object[] data = (Object[]) parameterValues[parameterIndex - 1];

        if (data == null) {
            return null;
        }

        return new JDBCArray(data, type.collectionBaseType(), type,
                             connection);
    }

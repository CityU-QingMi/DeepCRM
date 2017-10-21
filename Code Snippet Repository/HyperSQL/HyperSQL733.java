    public synchronized void setBlob(int parameterIndex,
                                     Blob x) throws SQLException {

        checkSetParameterIndex(parameterIndex);

        Type outType = parameterTypes[parameterIndex - 1];

        switch (outType.typeCode) {

            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
                setBlobForBinaryParameter(parameterIndex, x);

                return;
            case Types.SQL_BLOB :
                setBlobParameter(parameterIndex, x);

                break;
            default :
                throw JDBCUtil.invalidArgument();
        }
    }

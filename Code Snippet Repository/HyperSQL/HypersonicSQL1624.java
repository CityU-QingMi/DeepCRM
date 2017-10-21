    public synchronized void setClob(int parameterIndex,
                                     Clob x) throws SQLException {

        checkSetParameterIndex(parameterIndex);

        Type outType = parameterTypes[parameterIndex - 1];

        switch (outType.typeCode) {

            case Types.SQL_CHAR :
            case Types.SQL_VARCHAR :
                setClobForStringParameter(parameterIndex, x);

                return;
            case Types.SQL_CLOB :
                setClobParameter(parameterIndex, x);

                return;
            default :
                throw JDBCUtil.invalidArgument();
        }
    }

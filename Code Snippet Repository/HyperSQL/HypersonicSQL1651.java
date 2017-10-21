    void setLongParameter(int i, long value) throws SQLException {

        checkSetParameterIndex(i);

        int outType = parameterTypes[i - 1].typeCode;

        switch (outType) {

            case Types.SQL_BIGINT :
                Object o = Long.valueOf(value);

                parameterValues[i - 1] = o;
                parameterSet[i - 1]    = Boolean.TRUE;

                break;
            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
            case Types.OTHER :
                throw JDBCUtil.sqlException(Error.error(ErrorCode.X_42563));
            default :
                setParameter(i, Long.valueOf(value));
        }
    }

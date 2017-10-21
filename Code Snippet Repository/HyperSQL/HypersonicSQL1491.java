    public synchronized BigDecimal getBigDecimal(
            int parameterIndex) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        Type targetType = parameterMetaData.columnTypes[parameterIndex - 1];

        switch (targetType.typeCode) {

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                break;
            case Types.TINYINT :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER :
            case Types.SQL_BIGINT :
                targetType = Type.SQL_DECIMAL;

                break;
            case Types.SQL_DOUBLE :
            default :
                targetType = Type.SQL_DECIMAL_DEFAULT;

                break;
        }

        return (BigDecimal) getColumnInType(parameterIndex, targetType);
    }

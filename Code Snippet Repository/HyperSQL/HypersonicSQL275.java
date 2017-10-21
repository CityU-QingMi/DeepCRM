    synchronized Object getValueObject() {

        long   value = getValue();
        Object result;

        switch (dataType.typeCode) {

            default :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER :
                result = ValuePool.getInt((int) value);
                break;

            case Types.SQL_BIGINT :
                result = ValuePool.getLong(value);
                break;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                result = ValuePool.getBigDecimal(new BigDecimal(value));
                break;
        }

        return result;
    }

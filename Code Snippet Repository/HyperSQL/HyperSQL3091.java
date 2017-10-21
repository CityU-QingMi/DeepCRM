    public String getJDBCClassName() {

        switch (typeCode) {

            case Types.TINYINT :
            case Types.SQL_SMALLINT :
            case Types.SQL_INTEGER :
                return "java.lang.Integer";

            case Types.SQL_BIGINT :
                return "java.lang.Long";

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return "java.lang.Double";

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return "java.math.BigDecimal";

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }

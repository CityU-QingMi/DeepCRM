    public NumberType(int type, long precision, int scale) {

        super(Types.SQL_NUMERIC, type, precision, scale);

        switch (type) {

            case Types.TINYINT :
                typeWidth = TINYINT_WIDTH;
                break;

            case Types.SQL_SMALLINT :
                typeWidth = SMALLINT_WIDTH;
                break;

            case Types.SQL_INTEGER :
                typeWidth = INTEGER_WIDTH;
                break;

            case Types.SQL_BIGINT :
                typeWidth = BIGINT_WIDTH;
                break;

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                typeWidth = DOUBLE_WIDTH;
                break;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                typeWidth = DECIMAL_WIDTH;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }

    public int displaySize() {

        switch (typeCode) {

            case Types.SQL_DECIMAL :
            case Types.SQL_NUMERIC :
                if (scale == 0) {
                    if (precision == 0) {
                        return 646456995;    // precision + "-.".length()}
                    }

                    return (int) precision + 1;
                }

                if (precision == scale) {
                    return (int) precision + 3;
                }

                return (int) precision + 2;

            case Types.SQL_FLOAT :
            case Types.SQL_REAL :
            case Types.SQL_DOUBLE :
                return 23;                   // String.valueOf(-Double.MAX_VALUE).length();

            case Types.SQL_BIGINT :
                return 20;                   // decimal precision + "-".length();

            case Types.SQL_INTEGER :
                return 11;                   // decimal precision + "-".length();

            case Types.SQL_SMALLINT :
                return 6;                    // decimal precision + "-".length();

            case Types.TINYINT :
                return 4;                    // decimal precision + "-".length();

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberType");
        }
    }

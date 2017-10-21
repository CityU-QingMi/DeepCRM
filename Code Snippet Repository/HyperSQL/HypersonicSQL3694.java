    private String getNameStringPrivate() {

        switch (typeCode) {

            case Types.SQL_DATE :
                return Tokens.T_DATE;

            case Types.SQL_TIME :
                return Tokens.T_TIME;

            case Types.SQL_TIME_WITH_TIME_ZONE :
                return Tokens.T_TIME + ' ' + Tokens.T_WITH + ' '
                       + Tokens.T_TIME + ' ' + Tokens.T_ZONE;

            case Types.SQL_TIMESTAMP :
                return Tokens.T_TIMESTAMP;

            case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                return Tokens.T_TIMESTAMP + ' ' + Tokens.T_WITH + ' '
                       + Tokens.T_TIME + ' ' + Tokens.T_ZONE;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "DateTimeType");
        }
    }

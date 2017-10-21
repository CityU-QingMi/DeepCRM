    private static int getExtractTokenForTSIPart(int part) {

        switch (part) {

            case Tokens.SQL_TSI_FRAC_SECOND :
                return Tokens.NANOSECOND;

            case Tokens.SQL_TSI_MILLI_SECOND :
                return Tokens.MILLISECOND;

            case Tokens.SQL_TSI_SECOND :
                return Tokens.SECOND;

            case Tokens.SQL_TSI_MINUTE :
                return Tokens.MINUTE;

            case Tokens.SQL_TSI_HOUR :
                return Tokens.HOUR;

            case Tokens.SQL_TSI_DAY :
                return Tokens.DAY;

            case Tokens.DAY_OF_WEEK :
                return Tokens.DAY_OF_WEEK;

            case Tokens.DAY_OF_YEAR :
                return Tokens.DAY_OF_YEAR;

            case Tokens.TIMEZONE :
                return Tokens.TIMEZONE;

            case Tokens.SQL_TSI_WEEK :
                return Tokens.WEEK_OF_YEAR;

            case Tokens.SQL_TSI_MONTH :
                return Tokens.MONTH;

            case Tokens.SQL_TSI_QUARTER :
                return Tokens.QUARTER;

            case Tokens.SQL_TSI_YEAR :
                return Tokens.YEAR;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "FunctionCustom");
        }
    }

    public static HsqlException parseError(int code, int subCode,
                                           int lineNumber,
                                           final Object[] add) {

        String message = getMessage(code, subCode, add);

        if (lineNumber > 1) {
            String sub = getMessage(ErrorCode.M_parse_line);

            message = message + " :" + sub + String.valueOf(lineNumber);
        }

        int sqlCode = subCode < ERROR_CODE_BASE ? code
                                                : subCode;

        return new HsqlException(null,
                                 message.substring(SQL_STATE_DIGITS + 1),
                                 message.substring(0, SQL_STATE_DIGITS),
                                 -sqlCode);
    }

    public static HsqlException parseError(int code, String add,
                                           int lineNumber) {

        String s = getMessage(code);

        if (add != null) {
            s = s + ": " + add;
        }

        if (lineNumber > 1) {
            add = getMessage(ErrorCode.M_parse_line);
            s   = s + " :" + add + String.valueOf(lineNumber);
        }

        return new HsqlException(null, s.substring(SQL_STATE_DIGITS + 1),
                                 s.substring(0, SQL_STATE_DIGITS), -code);
    }

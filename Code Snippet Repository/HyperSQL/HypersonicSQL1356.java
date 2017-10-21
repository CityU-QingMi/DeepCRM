    public static HsqlException error(String message, String sqlState) {

        int code = getCode(sqlState);

        if (code < 1000) {
            code = ErrorCode.X_45000;
        }

        if (message == null) {
            message = getMessage(code);
        }

        return new HsqlException(null, message, sqlState, code);
    }

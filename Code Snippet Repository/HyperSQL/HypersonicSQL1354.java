    public static HsqlException error(Throwable t, int code, int subCode,
                                      final Object[] add) {

        String message = getMessage(code, subCode, add);
        int    sqlCode = subCode < ERROR_CODE_BASE ? code
                                                   : subCode;

        return new HsqlException(t, message.substring(SQL_STATE_DIGITS + 1),
                                 message.substring(0, SQL_STATE_DIGITS),
                                 -sqlCode);
    }

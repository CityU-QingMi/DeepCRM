    public static HsqlException error(Throwable t, int code, String add) {

        String s = getMessage(code);

        if (add != null) {
            s += ": " + add;
        }

        return new HsqlException(t, s.substring(SQL_STATE_DIGITS + 1),
                                 s.substring(0, SQL_STATE_DIGITS), -code);
    }

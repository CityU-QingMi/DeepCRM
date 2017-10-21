    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return a;
        }

        String s;

        if (a instanceof Boolean) {
            s = a.toString();
        } else if (a instanceof BigDecimal) {
            s = JavaSystem.toString((BigDecimal) a);
        } else if (a instanceof Number) {
            s = a.toString();    // use shortcut
        } else if (a instanceof String) {
            s = (String) a;
        } else if (a instanceof java.sql.Date) {
            s = a.toString();
        } else if (a instanceof java.sql.Time) {
            s = a.toString();
        } else if (a instanceof java.sql.Timestamp) {
            s = a.toString();
        } else if (a instanceof java.util.Date) {
            s = HsqlDateTime.getTimestampString(
                ((java.util.Date) a).getTime());
        } else if (a instanceof java.util.UUID) {
            s = a.toString();
        } else {
            s = convertJavaTimeObject(session, a);

            if (s == null) {
                throw Error.error(ErrorCode.X_42561);
            }
        }

        return s;
    }

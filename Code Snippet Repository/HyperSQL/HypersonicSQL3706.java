    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof String) {
            return convertToType(session, a, Type.SQL_VARCHAR);
        } else if (a instanceof Integer) {
            return convertToType(session, a, Type.SQL_INTEGER);
        } else if (a instanceof Long) {
            return convertToType(session, a, Type.SQL_BIGINT);
        } else if (a instanceof BigDecimal) {
            return convertToType(session, a, Type.SQL_DECIMAL);
        } else {
            throw Error.error(ErrorCode.X_42561);
        }
    }

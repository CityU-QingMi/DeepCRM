    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof Boolean) {
            return a;
        } else if (a instanceof String) {
            return convertToType(session, a, Type.SQL_VARCHAR);
        } else if (a instanceof Number) {
            return NumberType.isZero(a) ? Boolean.FALSE
                                        : Boolean.TRUE;
        }

        throw Error.error(ErrorCode.X_42561);
    }

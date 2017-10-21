    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof ClobData) {
            return a;
        }

        if (a instanceof String) {
            ClobData clob = session.createClob(((String) a).length());

            clob.setString(session, 0, (String) a);

            return clob;
        }

        throw Error.error(ErrorCode.X_42561);
    }

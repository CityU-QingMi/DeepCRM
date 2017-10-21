    public Object convertJavaToSQL(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof byte[]) {
            return new BinaryData((byte[]) a, true);
        }

        throw Error.error(ErrorCode.X_42561);
    }

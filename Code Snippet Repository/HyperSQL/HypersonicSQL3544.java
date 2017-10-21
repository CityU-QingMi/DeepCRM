    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return a;
        }

        if (a instanceof byte[]) {
            return new BinaryData((byte[]) a, false);
        } else if (a instanceof BinaryData) {
            return a;
        } else if (a instanceof String) {
            return castOrConvertToType(session, a, Type.SQL_VARCHAR, false);
        }

        throw Error.error(ErrorCode.X_22501);
    }

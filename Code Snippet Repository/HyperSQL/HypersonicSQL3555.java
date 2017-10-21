    public Object convertToDefaultType(SessionInterface session, Object a) {

        if (a == null) {
            return a;
        }

        if (a instanceof byte[]) {
            BinaryData b = new BinaryData((byte[]) a, false);

            castOrConvertToType(session, b, Type.SQL_VARBINARY, false);
        } else if (a instanceof BinaryData) {
            return castOrConvertToType(session, a, Type.SQL_VARBINARY, false);
        } else if (a instanceof String) {
            return castOrConvertToType(session, a, Type.SQL_VARCHAR, false);
        }

        throw Error.error(ErrorCode.X_22501);
    }

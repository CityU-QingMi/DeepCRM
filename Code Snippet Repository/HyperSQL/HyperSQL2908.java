    public Object convertToTypeJDBC(SessionInterface session, Object a,
                                    Type otherType) {

        if (a == null) {
            return a;
        }

        if (otherType.typeCode == Types.SQL_BLOB) {
            throw Error.error(ErrorCode.X_42561);
        }

        return convertToType(session, a, otherType);
    }

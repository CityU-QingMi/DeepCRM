    public Object convertToTypeJDBC(SessionInterface session, Object a,
                                    Type otherType) {

        if (a == null) {
            return a;
        }

        if (otherType.isLobType()) {
            throw Error.error(ErrorCode.X_42561);
        }

        switch (otherType.typeCode) {

            case Types.SQL_BOOLEAN :
                a         = ((Boolean) a).booleanValue() ? ValuePool.INTEGER_1
                                                         : ValuePool.INTEGER_0;
                otherType = Type.SQL_INTEGER;
        }

        return convertToType(session, a, otherType);
    }

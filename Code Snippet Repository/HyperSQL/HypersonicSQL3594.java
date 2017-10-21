    public Object convertToTypeJDBC(SessionInterface session, Object a,
                                    Type otherType) {

        if (a == null) {
            return a;
        }

        switch (otherType.typeCode) {

            case Types.SQL_BOOLEAN :
                return a;

            default :
                if (otherType.isLobType()) {
                    throw Error.error(ErrorCode.X_42561);
                }

                if (otherType.isCharacterType()) {
                    if ("0".equals(a)) {
                        return Boolean.FALSE;
                    } else if ("1".equals(a)) {
                        return Boolean.TRUE;
                    }
                }

                return convertToType(session, a, otherType);
        }
    }

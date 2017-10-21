    public long position(SessionInterface session, Object data,
                         Object otherData, Type otherType, long start) {

        if (otherType.typeCode == Types.SQL_CLOB) {
            return ((ClobData) data).position(session, (ClobData) otherData,
                                              start);
        } else if (otherType.isCharacterType()) {
            return ((ClobData) data).position(session, (String) otherData,
                                              start);
        } else {
            throw Error.runtimeError(ErrorCode.U_S0500, "ClobType");
        }
    }

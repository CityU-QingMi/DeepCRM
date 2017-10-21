    public long position(SessionInterface session, Object data,
                         Object otherData, Type otherType, long offset) {

        if (data == null || otherData == null) {
            return -1L;
        }

        if (otherType.typeCode == Types.SQL_CLOB) {
            long otherLength = ((ClobData) otherData).length(session);

            if (offset + otherLength > ((String) data).length()) {
                return -1;
            }

            if (otherLength > Integer.MAX_VALUE) {
                throw Error.error(ErrorCode.X_22026);
            }

            String otherString = ((ClobData) otherData).getSubString(session,
                0, (int) otherLength);

            return ((String) data).indexOf(otherString, (int) offset);
        } else if (otherType.isCharacterType()) {
            long otherLength = ((String) otherData).length();

            if (offset + otherLength > ((String) data).length()) {
                return -1;
            }

            return ((String) data).indexOf((String) otherData, (int) offset);
        } else {
            throw Error.runtimeError(ErrorCode.U_S0500, "CharacterType");
        }
    }

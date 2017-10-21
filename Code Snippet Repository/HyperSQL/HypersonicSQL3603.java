    public Object overlay(SessionInterface session, Object data,
                          Object overlay, long offset, long length,
                          boolean hasLength) {

        if (data == null || overlay == null) {
            return null;
        }

        if (!hasLength) {
            length = typeCode == Types.SQL_CLOB
                     ? ((ClobData) overlay).length(session)
                     : ((String) overlay).length();
        }

        Object temp = concat(null,
                             substring(session, data, 0, offset, true, false),
                             overlay);

        return concat(null, temp,
                      substring(session, data, offset + length, 0, false,
                                false));
    }

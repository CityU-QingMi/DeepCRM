    Boolean compare(Session session, Object o) {

        if (o == null) {
            return null;
        }

        if (isNull) {
            return null;
        }

        if (isIgnoreCase) {
            o = ((CharacterType) dataType).upper(session, o);
        }

        int length = getLength(session, o);

        if (o instanceof ClobData) {
            o = ((ClobData) o).getChars(session, 0,
                                        (int) ((ClobData) o).length(session));
        }

        return compareAt(session, o, 0, 0, iLen, length, cLike, wildCardType)
               ? Boolean.TRUE
               : Boolean.FALSE;
    }

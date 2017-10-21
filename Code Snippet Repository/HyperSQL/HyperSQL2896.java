    public Object concat(Session session, Object a, Object b) {

        if (a == null || b == null) {
            return null;
        }

        String left;
        String right;

        if (a instanceof ClobData) {
            left = ((ClobData) a).getSubString(
                session, 0, (int) ((ClobData) a).length(session));
        } else {
            left = (String) a;
        }

        if (b instanceof ClobData) {
            right = ((ClobData) b).getSubString(
                session, 0, (int) ((ClobData) b).length(session));
        } else {
            right = (String) b;
        }

        if (typeCode == Types.SQL_CLOB) {
            ClobData clob = session.createClob(left.length() + right.length());

            clob.setString(session, 0, left);
            clob.setString(session, left.length(), right);

            return clob;
        } else {
            return left + right;
        }
    }

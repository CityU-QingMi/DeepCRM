    public Object upper(Session session, Object data) {

        if (data == null) {
            return null;
        }

        if (typeCode == Types.SQL_CLOB) {
            String result = ((ClobData) data).getSubString(session, 0,
                (int) ((ClobData) data).length(session));

            result = collation.toUpperCase(result);

            ClobData clob = session.createClob(result.length());

            clob.setString(session, 0, result);

            return clob;
        }

        return collation.toUpperCase((String) data);
    }

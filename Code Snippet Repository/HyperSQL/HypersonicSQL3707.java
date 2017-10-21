    public Object convertJavaToSQL(SessionInterface session, Object a) {

        Object o = convertJavaTimeObject(session, a);

        if (o != null) {
            return o;
        }

        return convertToDefaultType(session, a);
    }

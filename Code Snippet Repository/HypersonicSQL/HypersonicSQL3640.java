    public Object convertJavaToSQL(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof JDBCClobClient) {
            return ((JDBCClobClient) a).getClob();
        }

        throw Error.error(ErrorCode.X_42561);
    }

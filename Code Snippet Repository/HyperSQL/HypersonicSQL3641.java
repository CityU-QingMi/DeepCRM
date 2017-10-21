    public Object convertSQLToJava(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof ClobDataID) {
            ClobDataID clob = (ClobDataID) a;

            return new JDBCClobClient(session, clob);
        }

        throw Error.error(ErrorCode.X_42561);
    }

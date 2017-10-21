    public Object convertJavaToSQL(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof JDBCBlobClient) {
            return ((JDBCBlobClient) a).getBlob();
        }

        throw Error.error(ErrorCode.X_42561);
    }

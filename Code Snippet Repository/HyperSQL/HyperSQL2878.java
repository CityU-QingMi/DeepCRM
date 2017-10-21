    public Object convertSQLToJava(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        if (a instanceof BlobDataID) {
            BlobDataID blob = (BlobDataID) a;

            return new JDBCBlobClient(session, blob);
        }

        throw Error.error(ErrorCode.X_42561);
    }

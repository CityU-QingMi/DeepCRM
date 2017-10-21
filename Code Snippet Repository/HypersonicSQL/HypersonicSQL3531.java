    public Object convertSQLToJava(SessionInterface session, Object a) {

        if (a instanceof Object[]) {
            Object[] data = (Object[]) a;

            return new JDBCArray(data, this.collectionBaseType(), this,
                                 session);
        }

        throw Error.error(ErrorCode.X_42561);
    }

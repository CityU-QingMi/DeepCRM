    public XAConnection getXAConnection(String user,
                                        String password) throws SQLException {

        if (user == null || password == null) {
            throw JDBCUtil.nullArgument();
        }

        if (user.equals(this.user) && password.equals(this.password)) {
            return getXAConnection();
        }

        throw JDBCUtil.sqlException(Error.error(ErrorCode.X_28000));
    }

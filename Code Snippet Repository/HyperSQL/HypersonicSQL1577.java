    public Connection getConnection() throws SQLException {

        if (url == null) {
            throw JDBCUtil.nullArgument("url");
        }

        if (connectionProps == null) {
            if (user == null) {
                throw JDBCUtil.invalidArgument("user");
            }

            if (password == null) {
                throw JDBCUtil.invalidArgument("password");
            }

            return getConnection(user, password);
        }

        return getConnection(url, connectionProps);
    }

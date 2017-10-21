    public Connection getConnection(String username, String password)
            throws SQLException {

        String user = getUser();

        if (username == null || password == null) {
            throw JDBCUtil.nullArgument();
        }

        if ( user == null) {
            setUser(username);
            setPassword(password);
        } else if (!user.equals(username)) {
            throw JDBCUtil.invalidArgument("user name does not match");
        }

        return getConnection();
    }

    public Connection getConnection(String username,
                                    String password) throws SQLException {

        if (username == null) {
            throw JDBCUtil.invalidArgument("user");
        }

        if (password == null) {
            throw JDBCUtil.invalidArgument("password");
        }

        Properties props = new Properties();

        props.setProperty("user", username);
        props.setProperty("password", password);
        props.setProperty("loginTimeout", Integer.toString(loginTimeout));

        return getConnection(url, props);
    }

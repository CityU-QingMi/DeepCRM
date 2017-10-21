    protected Connection getConnection(String username, String password,
                                       boolean ifExists) throws SQLException {

        Properties props = new Properties();

        props.put("user", username);
        props.put("password", password);
        props.put("ifexists", String.valueOf(ifExists));

        return DriverManager.getConnection(dbURL, props);
    }

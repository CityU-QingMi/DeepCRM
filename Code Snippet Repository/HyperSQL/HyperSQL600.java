    public void setProperties(Properties props) {

        connectionProps = (props == null) ? new Properties()
                                          : (Properties) props.clone();

        if (user != null) {
            connectionProps.setProperty("user", user);
        }

        if (password != null) {
            connectionProps.setProperty("password", password);
        }

        if (loginTimeout != 0) {
            connectionProps.setProperty("loginTimeout", Integer.toString(loginTimeout));
        }
    }

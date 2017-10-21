    public static DataSource createDataSource(Properties props)
    throws Exception {

        JDBCDataSource ds =
            (JDBCDataSource) Class.forName(bdsClassName).newInstance();
        String value = props.getProperty(databaseName);

        if (value == null) {
            value = props.getProperty(urlName);
        }

        ds.setDatabase(value);

        value = props.getProperty(userName);

        if (value == null) {
            value = props.getProperty(userNameName);
        }

        ds.setUser(value);

        value = props.getProperty(passwordName);

        ds.setPassword(value);

        value = props.getProperty(loginTimeoutName);

        if (value != null) {
            value = value.trim();

            if (value.length() > 0) {
                try {
                    ds.setLoginTimeout(Integer.parseInt(value));
                } catch (NumberFormatException nfe) {}
            }
        }

        return ds;
    }

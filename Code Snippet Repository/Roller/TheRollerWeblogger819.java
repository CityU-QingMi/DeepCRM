    public static Connection createConnection(Properties props, String prefix) 
        throws Exception
    {
        Connection con;
        if (prefix == null) {
            prefix = "";
        }
        String driverClassName = props.getProperty(prefix+"driverClassName");
        String connectionUrl = props.getProperty(prefix+"connectionUrl");
        String userName = props.getProperty(prefix+"userName");
        String password = props.getProperty(prefix+"password");
        
        Class.forName(driverClassName);
        if (userName != null && password != null) {
           con = DriverManager.getConnection(connectionUrl, userName, password);
        } else {
           con = DriverManager.getConnection(connectionUrl);
        }
        return con;
    }

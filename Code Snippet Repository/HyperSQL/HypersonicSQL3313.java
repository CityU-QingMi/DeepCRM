    protected void setUp() throws Exception {

        super.setUp();
        user     = "sa";
        password = "";
        stmt     = null;
        conn1    = null;
        conn2    = null;
        server   = new Server();

//        server = new WebServer();
        server.putPropertiesFromString(serverProps);
        server.start();

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            conn1 = DriverManager.getConnection(url, user, password);
            conn2 = DriverManager.getConnection(url, user, password);
            stmt  = conn1.createStatement();
        } catch (Exception e) {

            //e.printStackTrace();
            System.out.println(this + ".setUp() error: " + e.getMessage());
            throw e;
        }
    }

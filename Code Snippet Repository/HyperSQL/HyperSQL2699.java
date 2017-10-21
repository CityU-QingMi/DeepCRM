    protected void setUp() throws Exception {

        super.setUp();

        user       = "sa";
        password   = "";
        stmnt      = null;
        connection = null;

        TestUtil.deleteDatabase("/hsql/test/testpersistent");

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            connection = DriverManager.getConnection(url, user, password);
            stmnt      = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestSqlPersistence.setUp() error: "
                               + e.getMessage());
        }
    }

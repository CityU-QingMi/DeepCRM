    protected void setUp() {

        user     = "sa";
        password = "";

        try {
            sStatement  = null;
            cConnection = null;

            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            boolean createDatabase = true;

            if (createDatabase) {
                cConnection = DriverManager.getConnection(url, user, password);
                sStatement  = cConnection.createStatement();

                sStatement.execute("SET DATABASE EVENT LOG LEVEL 3");
                sStatement.execute("SET FILES LOG SIZE " + 100);
                sStatement.execute("SET FILES LOG TRUE");
                sStatement.execute("SET FILES WRITE DELAY " + writeDelay);
                sStatement.execute("SET FILES CACHE ROWS 600000");
                sStatement.execute("SET FILES CACHE SIZE 240000");
                sStatement.execute("SHUTDOWN");
                cConnection.close();

                cConnection = DriverManager.getConnection(url, user, password);
                sStatement  = cConnection.createStatement();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestSql.setUp() error: " + e.getMessage());
        }
    }

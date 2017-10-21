    protected void setUp() {

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            createDatabase();

            con = DriverManager.getConnection("jdbc:hsqldb:testdb", "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(this + ".setUp() error: " + e.getMessage());
        }
    }

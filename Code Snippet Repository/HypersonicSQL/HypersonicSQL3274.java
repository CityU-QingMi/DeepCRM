    protected void setUp() throws Exception {

        super.setUp();

        connection = newConnection();
        stmnt      = connection.createStatement();

        stmnt.execute(
            "CREATE TABLE IF NOT EXISTS link_table (id INTEGER PRIMARY KEY NOT NULL, other TINYINT NOT NULL)");
        stmnt.execute("INSERT INTO link_table VALUES ((0, 1),(1, 2))");
    }

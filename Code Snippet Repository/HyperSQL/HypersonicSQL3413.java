    public void setUp() throws Exception {
        super.setUp();
        conn = newConnection();

		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE PUBLIC.emp IF EXISTS");
		stmt.executeUpdate("CREATE TABLE PUBLIC.emp (emp_id INTEGER NOT NULL,name VARCHAR(30),salary DECIMAL(10,2),dept_id INTEGER,bus_start DATETIME NOT NULL,bus_end DATETIME NOT NULL);");
		
		stmt.executeUpdate("insert into PUBLIC.emp (emp_id, name, salary, dept_id, bus_start, bus_end)"
			+"values"
			+"(1, 'Tom', 300000.00, 1, TIMESTAMP '2000-01-01 01:02:03', TIMESTAMP '2000-02-01 01:02:03'),"
			+"(2, 'Tom', 305000.00, 1, TIMESTAMP '2000-02-01 01:02:03', TIMESTAMP '2000-03-01 01:02:03'),"
			+"(3, 'Tom', 310000.00, 1, TIMESTAMP '2000-03-01 01:02:03', TIMESTAMP '2000-04-01 01:02:03')"
			+";");
		
		stmt.close();
    }

    protected void setUp() throws Exception {

        super.setUp();

        if (conn != null) {
            return;
        }

        Class.forName(databaseDriver);

        conn = getJDBCConnection();
        stmt = conn.createStatement();

        // I decided not the use the "IF EXISTS" clause since it is not a
        // SQL standard.
        try {

//            stmt.execute("drop table employee");
            stmt.execute("drop table employee if exists");
        } catch (Exception x) {}

        stmt.execute("create table employee(id int, "
                     + "firstname VARCHAR(50), " + "lastname VARCHAR(50), "
                     + "salary decimal(10, 2), " + "superior_id int, "
                     + "CONSTRAINT PK_employee PRIMARY KEY (id), "
                     + "CONSTRAINT FK_superior FOREIGN KEY (superior_id) "
                     + "REFERENCES employee(ID))");
        addEmployee(1, "Mike", "Smith", 160000, -1);
        addEmployee(2, "Mary", "Smith", 140000, -1);

        // Employee under Mike
        addEmployee(10, "Joe", "Divis", 50000, 1);
        addEmployee(11, "Peter", "Mason", 45000, 1);
        addEmployee(12, "Steve", "Johnson", 40000, 1);
        addEmployee(13, "Jim", "Hood", 35000, 1);

        // Employee under Mike
        addEmployee(20, "Jennifer", "Divis", 60000, 2);
        addEmployee(21, "Helen", "Mason", 50000, 2);
        addEmployee(22, "Daisy", "Johnson", 40000, 2);
        addEmployee(23, "Barbara", "Hood", 30000, 2);
    }

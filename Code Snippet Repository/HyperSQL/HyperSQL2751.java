    public void testArrayA() {

        try {
            String ddl0 = "DROP TABLE ARRAYTEST IF EXISTS";
            String ddl1 = "CREATE TABLE ARRAYTEST(A INTEGER ARRAY)";
            String dml1 = "INSERT INTO ARRAYTEST VALUES(ARRAY[0,0])";
            String dml2 = "INSERT INTO ARRAYTEST VALUES ?";

            statement.execute(ddl0);
            statement.execute(ddl1);
            statement.execute(dml1);

            PreparedStatement ps      = connection.prepareStatement(dml2);
            Object[]          objects = new Object[] {
                "1", 3, 9
            };
            Array array = connection.createArrayOf("INTEGER", objects);

            ps.setArray(1, array);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            fail("array failure");
        }
    }

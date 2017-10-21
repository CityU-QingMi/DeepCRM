    public void testUnionColumnTypes() {

        try {
            Connection conn = newConnection();
            Statement  stmt = conn.createStatement();

            stmt.execute("DROP TABLE test1 IF EXISTS");
            stmt.execute("DROP TABLE test2 IF EXISTS");
            stmt.execute("CREATE TABLE test1 (id int, b1 boolean)");
            stmt.execute("CREATE TABLE test2 (id int)");
            stmt.execute("INSERT INTO test1 VALUES(1,true)");
            stmt.execute("INSERT INTO test2 VALUES(2)");

            ResultSet rs = stmt.executeQuery(
                "select id,null as b1 from test2 union select id, b1 from test1");
            Boolean[] array = new Boolean[2];

            for (int i = 0; rs.next(); i++) {
                boolean boole = rs.getBoolean(2);

                array[i] = Boolean.valueOf(boole);

                if (rs.wasNull()) {
                    array[i] = null;
                }
            }

            boolean result = (array[0] == null && array[1] == Boolean.TRUE)
                             || (array[0] == Boolean.TRUE && array[1] == null);

            assertTrue(result);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("TestSql.testUnionColumnType() error: "
                               + e.getMessage());
        }
    }

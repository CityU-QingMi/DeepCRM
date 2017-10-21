    public void testDeletable2() {

        try {
            Connection c =
                DriverManager.getConnection("jdbc:hsqldb:mem:mytestdb", "SA",
                                            "");
            String createSQL =
                "create table test (num INTEGER PRIMARY KEY, str VARCHAR(25))";
            Statement createStmt = c.createStatement();

            createStmt.execute(createSQL);
            createStmt.close();

            String            ins = "insert into test (num,str) values (?,?)";
            PreparedStatement pStmt = c.prepareStatement(ins);

            for (int i = 0; i < 100; i++) {
                pStmt.setInt(1, i);
                pStmt.setString(2, "String" + i);
                pStmt.execute();
            }

            // there should now be 100 rows in the table
            String select = "SELECT * FROM test";
            PreparedStatement stmt = c.prepareStatement(select,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();

            rs.beforeFirst();

            while (rs.next()) {
                int num = rs.getInt("num");

                if ((num % 7) == 0) {
                    System.out.println("Deleting row:" + num);
                    rs.deleteRow();
                }
            }

            Statement dropStmt = c.createStatement();

            dropStmt.execute("drop table test;");
            dropStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testMultiInsert() {

        try {

            // create table T and insert two rows simultaneously
            stmnt.execute("DROP TABLE T IF EXISTS;");
            stmnt.execute(
                "CREATE TABLE T (I IDENTITY, A CHAR(10), B CHAR(10));");
            stmnt.execute(
                "INSERT INTO T VALUES (NULL, 'A', 'a'),(NULL, 'B', 'b');");

            // print table out - should have two rows
            printTable("T", "*", 2);

            // 3 inserts - a normal standard syntax, multi-row syntax for
            //             single row, and multi-row syntax for two rows
            stmnt.execute("INSERT INTO T VALUES(NULL,'single1','s1');");
            stmnt.execute("INSERT INTO T VALUES((NULL,'single2','s2'));");
            stmnt.execute(
                "INSERT INTO T VALUES((NULL,'double1','d1'),(NULL,'double2','d2'));");

            // print table out - should have 6 rows
            printTable("T", "*", 6);

            // insert via a prepared statement - both single and multi rows
            pstmnt = connection.prepareStatement("INSERT INTO T VALUES (?,?,?)");
            pstmnt.setString(1, null);
            pstmnt.setString(2, "prepared1");
            pstmnt.setString(3, "test1");
            pstmnt.executeUpdate();
            pstmnt = connection.prepareStatement("INSERT INTO T VALUES (?,?,?),(null,?,?)");
            pstmnt.setString(1, null);
            pstmnt.setString(2, "prepared2");
            pstmnt.setString(3, "test2");
            pstmnt.setString(4, "prepared3");
            pstmnt.setString(5, "test3");
            pstmnt.executeUpdate();

            // print table out - should have 9 rows
            printTable("T", "*", 9);

        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMultiInsert complete");

    }

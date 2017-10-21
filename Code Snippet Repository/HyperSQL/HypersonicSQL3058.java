    private static void doSomeWork() throws SQLException {

        Connection conn = getConnection();
        Statement  stmt = conn.createStatement();

        conn.setAutoCommit(false);
        stmt.execute("INSERT INTO trig_test VALUES (1, 'hello')");
        stmt.execute("INSERT INTO trig_test VALUES (2, 'now what?')");
        stmt.execute("INSERT INTO trig_test VALUES (3, 'unchangable')");
        stmt.execute("INSERT INTO trig_test VALUES (4, 'goodbye')");
        conn.commit();
        dumpTable("trig_test");
        stmt.execute("UPDATE trig_test SET value = 'all done'");
        conn.commit();
        dumpTable("trig_test");
        stmt.execute("DELETE FROM trig_test");
        conn.rollback();
        dumpTable("trig_test");

        try {
            stmt.execute("INSERT INTO trig_test VALUES(11, 'whatever')");
        } catch (SQLException se) {
            se.printStackTrace();
        }

        stmt.execute("INSERT INTO trig_test VALUES(10, 'whatever')");
        conn.commit();
        dumpTable("trig_test");
        stmt.close();
        conn.close();
    }

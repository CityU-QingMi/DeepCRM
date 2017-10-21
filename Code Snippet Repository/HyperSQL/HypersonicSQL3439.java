    public static void procTest2(int p1, int p2,
                                 Integer[] p3) throws java.sql.SQLException {

        Connection conn =
            DriverManager.getConnection("jdbc:default:connection");
        java.sql.Statement stmt = conn.createStatement();

        stmt.execute("insert into mytable values(" + p1 + ",'test1')");
        stmt.execute("insert into mytable values(" + p2 + ",'test2')");

        java.sql.ResultSet rs = stmt.executeQuery("select * from mytable");
        java.sql.ResultSetMetaData meta = rs.getMetaData();
        int                        cols = meta.getColumnCount();

        p3[0] = Integer.valueOf(cols);

        rs.close();
        stmt.close();
    }

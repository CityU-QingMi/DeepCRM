    public static void procTest1(Connection conn)
    throws java.sql.SQLException {

        int                cols;
        java.sql.Statement stmt = conn.createStatement();

        stmt.execute("insert into mytable values(1,'test1');");
        stmt.execute("insert into mytable values(2,'test2');");

        java.sql.ResultSet rs = stmt.executeQuery("select * from mytable");
        java.sql.ResultSetMetaData meta = rs.getMetaData();

        cols = meta.getColumnCount();

        rs.close();
        stmt.close();
    }

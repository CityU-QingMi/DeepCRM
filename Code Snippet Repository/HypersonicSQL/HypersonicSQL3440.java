    public static ResultSet funcTest1(int p1,
                                      int p2) throws java.sql.SQLException {

        Connection conn =
            DriverManager.getConnection("jdbc:default:connection");
        java.sql.PreparedStatement stmt = conn.prepareStatement(
            "select * from mytable where col1 = ? or col1 = ?");

        stmt.setInt(1, p1);
        stmt.setInt(2, p2);

        java.sql.ResultSet rs = stmt.executeQuery();

        return rs;
    }

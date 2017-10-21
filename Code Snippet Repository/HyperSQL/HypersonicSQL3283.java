    protected void doInsert() {

        try {
            Connection con = getConnection("ABCD", "dcba", false);

            if (con != null) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "SELECT NEXT VALUE FOR MySeq FROM Dummy");

                rs.next();

                int id = rs.getInt(1);

                stmt.executeUpdate("INSERT INTO MyTable (Id, Name) VALUES ("
                                   + id + ", 'This is row #" + id + "')");
                append("Row #" + id + " added");
                stmt.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testSanity() throws SQLException {

        try {
            setupConn("db1");

            ResultSet rs =
                conn.createStatement().executeQuery("SELECT * FROM t;");

            rs.next();
            assertEquals("Wrong table 't' contents", 34, rs.getInt("i"));
        } finally {
            shutdownAndCloseConn();
        }
    }

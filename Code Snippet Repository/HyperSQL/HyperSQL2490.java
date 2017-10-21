    public void testDelete() {

        try {
            insertData(con);

            Statement stmt = con.createStatement();
            ResultSet rs =
                stmt.executeQuery("SELECT COUNT(EIACODXA) FROM CA");

            rs.next();

            int origCount = rs.getInt(1);

            rs.close();
            deleteXBRecord(con);

            rs = stmt.executeQuery("SELECT COUNT(EIACODXA) FROM CA");

            rs.next();

            int newCount = rs.getInt(1);

            rs.close();
            stmt.close();
            assertEquals(9, newCount);
        } catch (SQLException e) {
            this.assertTrue("SQLException thrown", false);
        }
    }

    public void testOne() throws SQLException {

        Connection        c = newConnection();
        CallableStatement s = c.prepareCall("CALL TEST_QUERY(16)");

        s.execute();

        ResultSet r = s.getResultSet();

        while (r.next()) {
            String temp = "" + r.getInt(2) + " " + r.getString(1);

            System.out.println(temp);
        }

        s = c.prepareCall("CALL TEST_CUSTOM_RESULT(6, 19)");

        s.execute();

        r = s.getResultSet();

        while (r.next()) {
            String temp =
                "" + r.getLong(1) + " "
                + org.hsqldb.lib.StringConverter.byteArrayToSQLHexString(
                    r.getBytes(2));

            System.out.println(temp);
        }

        r = s.executeQuery();
        s = c.prepareCall("CALL TEST_CUSTOM_RESULT(6, 1900)");

        try {
            s.execute();

            r = s.getResultSet();

            fail("exception not thrown");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        c.close();
    }

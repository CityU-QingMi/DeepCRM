    private static void doCreateTableTest(Connection p_connection)
    throws SQLException {

        System.out.println("CREATE TESTTABLE START ...");

        Statement st = p_connection.createStatement();

        st.executeUpdate("DROP TABLE TEST IF EXISTS");
        st.executeUpdate("CREATE TABLE TEST (TEST INTEGER)");
        st.close();
        System.out.println("END CREATE TESTTABLE");
        System.out.println("INSERT INTO TESTTABLE START ...");

        PreparedStatement p = p_connection.prepareStatement(
            "INSERT INTO TEST (TEST) values (?)");

        p.setInt(1, 123);
        p.execute();
        p.close();
        System.out.println("END INSERT INTO TESTTABLE");
    }

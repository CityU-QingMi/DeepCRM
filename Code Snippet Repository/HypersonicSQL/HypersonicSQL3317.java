    void prepareDatabase() throws SQLException {

        Connection c = newConnection();
        Statement  s = c.createStatement();

        s.executeUpdate("DROP FUNCTION TEST_QUERY IF EXISTS");
        s.executeUpdate("DROP FUNCTION TEST_CUSTOM_RESULT IF EXISTS");
        s.executeUpdate("DROP FUNCTION SORT_BYTE_ARRAY IF EXISTS");
        s.executeUpdate("DROP FUNCTION SORT_BINARY_ARRAY IF EXISTS");
        s.executeUpdate("DROP TABLE T IF EXISTS");
        s.executeUpdate("CREATE TABLE T(C VARCHAR(20), I INT)");
        s.executeUpdate("INSERT INTO T VALUES 'Thames', 10");
        s.executeUpdate("INSERT INTO T VALUES 'Fleet', 12");
        s.executeUpdate("INSERT INTO T VALUES 'Brent', 14");
        s.executeUpdate("INSERT INTO T VALUES 'Westbourne', 16");
        s.executeUpdate(
            "CREATE FUNCTION TEST_QUERY(INT) RETURNS TABLE(N VARCHAR(20), I INT) "
            + " READS SQL DATA LANGUAGE JAVA EXTERNAL NAME 'CLASSPATH:org.hsqldb.test.TestJavaFunctions.getQueryResult'");
        s.executeUpdate(
            "CREATE FUNCTION TEST_CUSTOM_RESULT(BIGINT, BIGINT) RETURNS TABLE(I BIGINT, N VARBINARY(1000)) "
            + " READS SQL DATA LANGUAGE JAVA EXTERNAL NAME 'CLASSPATH:org.hsqldb.test.TestJavaFunctions.getCustomResult'");
        s.executeUpdate(
            "CREATE FUNCTION SORT_BYTE_ARRAY(VARBINARY(20)) RETURNS VARBINARY(20) "
            + " NO SQL LANGUAGE JAVA EXTERNAL NAME 'CLASSPATH:org.hsqldb.test.TestJavaFunctions.getSortedByteArray'");
        s.executeUpdate(
            "CREATE FUNCTION SORT_BINARY_ARRAY(VARBINARY(20) ARRAY) RETURNS VARBINARY(20) ARRAY "
            + " NO SQL LANGUAGE JAVA EXTERNAL NAME 'CLASSPATH:org.hsqldb.test.TestJavaFunctions.getSortedArrayByteArray'");
        s.executeUpdate("CHECKPOINT");
        c.close();
    }

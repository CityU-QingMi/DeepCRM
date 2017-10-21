    public void test2pAliases() throws Exception {

        String prefix = "public.";

        // All occurrences of "expect" in this method indicate bugs.
        // When fixed, don't change the value of "expect" in the method body.
        int expect = 0;

        expect = SQL_ABORT;

        execSQL(
            "CREATE FUNCTION " + prefix + "tstalias(A VARCHAR(100)) "
            + "RETURNS VARCHAR(100) "
            + "LANGUAGE JAVA EXTERNAL NAME \'org.hsqldb.test.BlaineTrig.capitalize\'", 0);

        // Following should not throw an exception:
        assertEquals(
            1, queryRowCount(
                "SELECT " + prefix
                + "tstalias('helo') FROM tsttbl WHERE i = 1"));
    }

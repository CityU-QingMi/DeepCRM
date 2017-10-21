    public void test() throws java.lang.Exception {

        TestUtil.deleteDatabase("test");

        Connection conn     = newConnection();
        String     fullPath = "testrun/hsqldb/" + path;

        TestUtil.testScript(conn, fullPath);
//        conn.createStatement().execute("SHUTDOWN");
    }

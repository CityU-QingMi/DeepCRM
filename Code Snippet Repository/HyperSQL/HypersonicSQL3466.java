    void partA() throws Exception {

        Connection conn = newConnection();

        TestUtil.testScript(conn, "testrun/hsqldb/TestText01.txt");

        Statement st = conn.createStatement();

        st.execute("SHUTDOWN");
    }

    void partD() throws Exception {

        Connection conn = newConnection();

        TestUtil.testScript(conn, "testrun/hsqldb/TestText02.txt");

        Statement st = conn.createStatement();

        st.execute("SHUTDOWN");
    }

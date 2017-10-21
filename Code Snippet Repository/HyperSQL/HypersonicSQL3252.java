    public void testThree() throws Exception {

        Connection conn = newConnection();
        int        updateCount;

        try {
            TestUtil.testScript(conn, "testrun/hsqldb/TestSelf.txt");

            DatabaseMetaData dbmeta     = conn.getMetaData();
            int txIsolation = dbmeta.getDefaultTransactionIsolation();
            String           userName   = dbmeta.getUserName();
            boolean          isReadOnly = dbmeta.isReadOnly();
        } catch (Exception e) {
            assertTrue("unable to prepare or execute DDL", false);
        } finally {
            conn.close();
        }
    }

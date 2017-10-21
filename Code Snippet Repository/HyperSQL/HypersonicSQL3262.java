    protected void setUp() throws IOException, SQLException {

        if (verbose) {
            System.err.println("Set-upping");
        }

        if (baseDir.exists()) {
            throw new IOException("Please wipe out work directory '" + baseDir
                                  + ", which is probably left over from an "
                                  + "aborted test run");
        }

        try {
            setupConn("db1");

            Statement st = conn.createStatement();

            st.executeUpdate("CREATE TABLE t(i int);");
            st.executeUpdate("INSERT INTO t values(34);");
            conn.commit();
        } catch (SQLException se) {}
        finally {
            shutdownAndCloseConn();
        }
    }

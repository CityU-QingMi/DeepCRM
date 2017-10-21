    static void testScripts(String directory, StopWatch sw) {

        TestUtil.deleteDatabase("test1");

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            String     url = "jdbc:hsqldb:test1;sql.enforce_strict_size=true";
            String     user        = "sa";
            String     password    = "";
            Connection cConnection = null;
            String[]   filelist;
            String     absolute = new File(directory).getAbsolutePath();

            filelist = new File(absolute).list();

            ArraySort.sort((Object[]) filelist, 0, filelist.length,
                           new StringComparator());

            for (int i = 0; i < filelist.length; i++) {
                String fname = filelist[i];

                if (fname.startsWith("TestSelf") && fname.endsWith(".txt")) {
                    long elapsed = sw.elapsedTime();

                    if (!oneSessionOnly || cConnection == null) {
                        cConnection = DriverManager.getConnection(url, user,
                                password);
                    }

                    print("Opened DB in "
                          + (double) (sw.elapsedTime() - elapsed) / 1000
                          + " s");
                    testScript(cConnection, absolute + File.separator + fname);

                    if (!oneSessionOnly) {
                        cConnection.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            print("TestUtil init error: " + e.toString());
        }
    }

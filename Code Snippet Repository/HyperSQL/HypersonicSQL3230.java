    protected void setUp() {

        try {
            writer = new FileWriter("speedtests.html", true);

            writer.write("<table>\n");
            storeResult(new java.util.Date().toString(), 0, 0, 0);
            storeResult(filepath + " " + tableType + " " + nioMode,
                        cacheScale, 0, 0);
        } catch (Exception e) {}

        user     = "sa";
        password = "";

        try {
            sStatement  = null;
            cConnection = null;

            Class.forName("org.hsqldb.jdbc.JDBCDriver");

            if (filedb) {
                deleteDatabase(filepath);

                cConnection = DriverManager.getConnection(url + filepath,
                        user, password);
                sStatement = cConnection.createStatement();

//                sStatement.execute("SET FILES WRITE DELAY " + 2);
                sStatement.execute("SET FILES DEFRAG " + 0);
                sStatement.execute("SET FILES LOG SIZE " + 0);

//                sStatement.execute("SET FILES LOG FALSE");
                sStatement.execute("SET DATABASE EVENT LOG LEVEL 1");

                int cacheRows = (1 << cacheScale) * 3;
                int cacheSize = (1 << cacheSizeScale) * cacheRows / 1024;

                sStatement.execute("SET FILES CACHE ROWS " + cacheRows);
                sStatement.execute("SET FILES CACHE SIZE " + cacheSize);
                sStatement.execute("SET FILES NIO " + nioMode);
                sStatement.execute("SET FILES BACKUP INCREMENT " + true);
                sStatement.execute("SHUTDOWN");
                cConnection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TestSql.setUp() error: " + e.getMessage());
        }
    }

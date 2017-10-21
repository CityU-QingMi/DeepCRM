    protected void checkResults() {

        try {
            StopWatch sw = new StopWatch();
            ResultSet rs;

            cConnection = DriverManager.getConnection(url, user, password);

            System.out.println("Reopened database: " + sw.elapsedTime());
            sw.zero();

            sStatement = cConnection.createStatement();

            sStatement.execute("SET FILES WRITE DELAY " + writeDelay);
//            sStatement.execute("SET DATABASE EVENT LOG SQL LEVEL 3");

            // the tests use different indexes
            // use primary index
            sStatement.execute("SELECT count(*) from TEST");

            rs = sStatement.getResultSet();

            rs.next();
            System.out.println("Row Count: " + rs.getInt(1));
            System.out.println("Time to count: " + sw.elapsedTime());

            // use index on zip
            sw.zero();
            sStatement.execute("SELECT count(*) from TEST where zip > -1");

            rs = sStatement.getResultSet();

            rs.next();
            System.out.println("Row Count: " + rs.getInt(1));
            System.out.println("Time to count: " + sw.elapsedTime());
            checkSelects();
            checkUpdates();
            sw.zero();
            cConnection.close();
            System.out.println("Closed connection: " + sw.elapsedTime());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

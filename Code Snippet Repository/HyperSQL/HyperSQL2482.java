    protected void checkResults() {

        try {
            StopWatch sw = new StopWatch();
            ResultSet rs;

            cConnection = DriverManager.getConnection(url + filepath, user,
                    password);

            long time = sw.elapsedTime();

            storeResult("reopen", 0, time, 0);
            System.out.println("database reopen time -- " + time + " ms");
            sw.zero();

            sStatement = cConnection.createStatement();

//            sStatement.execute("SET WRITE_DELAY " + writeDelay);
            checkSelects();
            checkUpdates();
            sw.zero();

            if (shutdown) {
                sStatement.execute("SHUTDOWN");

                time = sw.elapsedTime();

                storeResult("shutdown", 0, time, 0);
                System.out.println("shutdown time  -- " + time + " ms");
            }

            cConnection.close();

//            System.out.println("database close time  -- " + sw.elapsedTime() + " ms");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

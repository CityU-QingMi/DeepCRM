    private void countTestID() {

        try {
            StopWatch sw = new StopWatch();

            // the tests use different indexes
            // use primary index
            sStatement.execute("SELECT count(*) from TEST where id > -1");

            ResultSet rs = sStatement.getResultSet();

            rs.next();

            long time = sw.elapsedTime();
            long rate = ((long) bigrows * 1000) / (time + 1);

            storeResult("count (index on id)", rs.getInt(1), time, rate);
            System.out.println("count time (index on id) " + rs.getInt(1)
                               + " rows  -- " + time + " ms -- " + rate
                               + " tps");

            sw.zero();

            sStatement.execute("SELECT count(*) from TEST");

            rs = sStatement.getResultSet();

            rs.next();

            time = sw.elapsedTime();
            rate = (1000L) / (time + 1);

            storeResult("count (index on id)", rs.getInt(1), time, rate);
            System.out.println("count time (full count) " + rs.getInt(1)
                               + " rows  -- " + time + " ms -- " + rate
                               + " tps");

        } catch (SQLException e) {}
    }

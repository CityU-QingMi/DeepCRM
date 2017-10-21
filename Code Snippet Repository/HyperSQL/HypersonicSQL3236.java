    private void countTestZip() {

        try {
            StopWatch sw = new StopWatch();

            sStatement.execute("SELECT count(*) from TEST where zip > -1");

            ResultSet rs = sStatement.getResultSet();

            rs.next();

            long time = (long) sw.elapsedTime();
            long rate = ((long) bigrows * 1000) / (time + 1);

            storeResult("count (index on zip)", rs.getInt(1), time, rate);
            System.out.println("count time (index on zip) " + rs.getInt(1)
                               + " rows  -- " + time + " ms -- " + rate
                               + " tps");
        } catch (SQLException e) {}
    }

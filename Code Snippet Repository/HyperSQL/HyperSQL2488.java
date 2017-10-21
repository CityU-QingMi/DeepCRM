    private void countZip() {

        try {
            StopWatch sw = new StopWatch();

            sStatement.execute("SELECT count(*) from zip where zip > -1");

            ResultSet rs = sStatement.getResultSet();

            rs.next();
            System.out.println("count time (zip table) " + rs.getInt(1)
                               + " rows  -- " + sw.elapsedTime() + " ms");
        } catch (SQLException e) {}
    }

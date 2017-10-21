    void selectID() {

        StopWatch        sw        = new StopWatch();
        java.util.Random randomgen = new java.util.Random();
        int              i         = 0;
        boolean          slow      = false;

        try {
            PreparedStatement ps = cConnection.prepareStatement(
                "SELECT firstname,lastname,zip,filler FROM test WHERE id = ?");

            for (i = 0; i < smallops; i++) {
                ps.setInt(1, nextIntRandom(randomgen, bigrows - 1));
                ps.execute();

                if (reportProgress && (i + 1) % 10000 == 0
                        || (slow && (i + 1) % 100 == 0)) {
                    System.out.println("Select " + (i + 1) + " : "
                                       + (sw.elapsedTime() + 1));
                }
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long time = sw.elapsedTime();
        long rate = ((long) i * 1000) / (time + 1);

        storeResult("select random id", i, time, rate);
        System.out.println("select time for random id " + i + " rows  -- "
                           + time + " ms -- " + rate + " tps");
    }

    void selectZip() {

        StopWatch        sw        = new StopWatch();
        java.util.Random randomgen = new java.util.Random();
        int              i         = 0;
        boolean          slow      = false;

        try {
            PreparedStatement ps = cConnection.prepareStatement(
                "SELECT TOP 1 firstname,lastname,zip,filler FROM test WHERE zip = ?");

            for (; i < bigops; i++) {
                ps.setInt(1, nextIntRandom(randomgen, smallrows));
                ps.execute();

                if ((i + 1) == 100 && sw.elapsedTime() > 50000) {
                    slow = true;
                }

                if (reportProgress && (i + 1) % 10000 == 0
                        || (slow && (i + 1) % 100 == 0)) {
                    System.out.println("Select " + (i + 1) + " : "
                                       + sw.elapsedTime() + " rps: "
                                       + (i * 1000 / (sw.elapsedTime() + 1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long time = sw.elapsedTime();
        long rate = ((long) i * 1000) / (time + 1);

        storeResult("select random zip", i, time, rate);
        System.out.println("select time for random zip " + i + " rows  -- "
                           + time + " ms -- " + rate + " tps");
    }

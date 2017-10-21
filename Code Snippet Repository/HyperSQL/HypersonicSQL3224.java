    void updateTestString() {

        StopWatch        sw        = new StopWatch();
        java.util.Random randomgen = new java.util.Random();
        int              i         = 0;
        boolean          slow      = false;
        int              count     = 0;
        int              random    = 0;

        try {
            PreparedStatement ps = cConnection.prepareStatement(
                "UPDATE test SET filler = ? WHERE id = ? and zip <> "
                + smallrows);

            for (i = 0; i < smallops * 2; i++) {
                random = nextIntRandom(randomgen, bigrows - 1);

                int randomLength = nextIntRandom(randomgen, filler.length());
                String newFiller = filler.substring(randomLength);

                ps.setString(1, newFiller);
                ps.setInt(2, random);
                ps.execute();

                if (reportProgress && (i + 1) % 10000 == 0
                        || (slow && (i + 1) % 100 == 0)) {
                    System.out.println("Update " + (i + 1) + " : "
                                       + sw.elapsedTime() + " rps: "
                                       + (i * 1000 / (sw.elapsedTime() + 1)));
                }
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("error : " + random);
            e.printStackTrace();
        }

        long time = sw.elapsedTime();
        long rate = (i * 1000) / (time + 1);

        storeResult("update with random id", i, time, rate);
        System.out.println("update time with random id " + i + " rows  -- "
                           + time + " ms -- " + rate + " tps");
    }

    private void updateZip() {

        StopWatch        sw        = new StopWatch();
        java.util.Random randomgen = new java.util.Random();
        int              i         = 0;
        boolean          slow      = false;
        int              count     = 0;
        int              random    = 0;

        try {
            PreparedStatement ps = cConnection.prepareStatement(
                "UPDATE test SET filler = filler || zip WHERE zip = ?");

            for (; i < smallrows; i++) {
                random = nextIntRandom(randomgen, smallrows - 1);

                ps.setInt(1, random);

                count += ps.executeUpdate();

                if (reportProgress && count % 10000 < 20) {
                    System.out.println("Update " + count + " : "
                                       + (sw.elapsedTime() + 1));
                }
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("error : " + random);
            e.printStackTrace();
        }

        long time = sw.elapsedTime();
        long rate = (i * 1000) / (time + 1);

        storeResult("update with random zip", i, time, rate);
        System.out.println("update time with random zip " + i + " rows  -- "
                           + time + " ms -- " + rate + " tps");
    }

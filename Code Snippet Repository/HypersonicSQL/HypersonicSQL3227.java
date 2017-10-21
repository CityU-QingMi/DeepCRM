    void deleteZipTable() {

        StopWatch        sw        = new StopWatch();
        java.util.Random randomgen = new java.util.Random();
        int              i         = 0;
        boolean          slow      = false;
        int              count     = 0;
        int              random    = 0;

        try {
            PreparedStatement ps =
                cConnection.prepareStatement("DELETE FROM zip WHERE zip = ?");

            for (i = 0; i <= smallrows; i++) {

//                random = randomgen.nextInt(smallrows - 1);
                random = i;

                ps.setInt(1, random);

                count += ps.executeUpdate();

                if (reportProgress && (i + 1) % 10000 == 0
                        || (slow && (i + 1) % 100 == 0)) {
                    System.out.println("delete " + (i + 1) + " : "
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
        long rate = ((long) count * 1000) / (time + 1);

        storeResult("delete with random zip", count, time, rate);
        System.out.println("delete time for random zip " + count
                           + " rows  -- " + time + " ms -- " + rate + " tps");
    }

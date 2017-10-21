    @AfterClass
    public static void shutdownDb() throws Exception {

        // shut down the scheduler
        LOG.info("------- Shutting Down Scheduler---------------------");
        sched.shutdown(true);
        LOG.info("------- Shutdown Complete -----------------");
        try {
            LOG.info("------- Destroying Database ---------------------");
            JdbcQuartzDerbyUtilities.destroyDatabase();
            LOG.info("------- Database destroyed ---------------------");
        } catch (SQLException e) {
            e.printStackTrace();
            e.getNextException().printStackTrace();
            throw new AssertionError(e);
        }

        derbyServer.shutdown();
        LOG.info("------- Database shutdown ---------------------");

    }

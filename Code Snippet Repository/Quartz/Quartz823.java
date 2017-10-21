    @BeforeClass
    public static void initialize() throws Exception {
        LOG.info("Starting DERBY database.");
        derbyServer = new NetworkServerControl();
        derbyServer.start(new PrintWriter(System.out));
        int tries = 0;
        while (tries < 5) {
            try {
                Thread.sleep(500);
                derbyServer.ping();
                break;
            } catch (Exception e) {
                tries++;
            }
        }
        if (tries == 5) {
            throw new Exception("Failed to start Derby!");
        }
        LOG.info("Database started.");
        try {
            LOG.info("Creating Database tables for Quartz.");
            JdbcQuartzDerbyUtilities.createDatabase();
            LOG.info("Database tables created.");
        } catch (SQLException e) {
            throw new Exception("Failed to create Quartz tables.", e);
        }
    }

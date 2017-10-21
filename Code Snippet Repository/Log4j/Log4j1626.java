    public static void main(final String[] args) {
        System.setProperty("log4j.configurationFile", "target/test-classes/log4j-cronRolloverApp.xml");
        logger = LogManager.getLogger(CronRolloverApp.class);
        try {
            for (int i = 1; i <= 240; i++) {
                logger.info("Hello");
                Thread.sleep(1 * 1000);
            }
        } catch (final Exception e) {
            //e.printStackTrace();
            logger.error("Excepcion general", e);
        }
    }

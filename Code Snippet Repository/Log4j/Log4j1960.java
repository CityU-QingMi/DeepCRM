    @Test
    public void testLog4j2_807() throws InterruptedException, URISyntaxException {
        final URL url = AsyncRootReloadTest.class.getResource("/" + ISSUE_CONFIG);
        final File configFile = FileUtils.fileFromUri(url.toURI());

        final Logger logger = LogManager.getLogger(AsyncRootReloadTest.class);
        logger.info("Log4j configured, will be reconfigured in aprox. 5 sec");

        configFile.setLastModified(System.currentTimeMillis());

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            logger.info("Log4j waiting for reconfiguration");
        }
    }

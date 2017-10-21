    @Test
    public void test() throws IOException {
        final Logger logger = LogManager.getLogger();
        logger.info(SQL, SQL_LOG);
        logger.info(PAYLOAD, PAYLOAD_LOG);
        logger.info(PERFORMANCE, PERFORMANCE_LOG);
        {
            final String log = FileUtils.readFileToString(new File("target/logs/sql.log"));
            Assert.assertTrue(log.contains(SQL_LOG));
            Assert.assertFalse(log.contains(PAYLOAD_LOG));
            Assert.assertFalse(log.contains(PERFORMANCE_LOG));
        }
        {
            final String log = FileUtils.readFileToString(new File("target/logs/payload.log"));
            Assert.assertFalse(log.contains(SQL_LOG));
            Assert.assertTrue(log.contains(PAYLOAD_LOG));
            Assert.assertFalse(log.contains(PERFORMANCE_LOG));
        }
        {
            final String log = FileUtils.readFileToString(new File("target/logs/performance.log"));
            Assert.assertFalse(log.contains(SQL_LOG));
            Assert.assertFalse(log.contains(PAYLOAD_LOG));
            Assert.assertTrue(log.contains(PERFORMANCE_LOG));
        }
    }

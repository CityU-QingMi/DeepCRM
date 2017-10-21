    @Test
    public void testNoConcurrentModificationException() throws Exception {
        System.setProperty(ConfigurationFactory.CONFIGURATION_FILE_PROPERTY,
                "AsyncLoggerConfigTest2.xml");
        final File file = new File("target", "AsyncLoggerConfigTest2.log");
        assertTrue("Deleted old file before test", !file.exists() || file.delete());

        final Logger log = LogManager.getLogger("com.foo.Bar");
        log.info("initial message");
        Thread.sleep(500);

        final Map<String, String> map = new HashMap<>();
        for (int j = 0; j < 3000; j++) {
            map.put(String.valueOf(j), String.valueOf(System.nanoTime()));
        }

        final Message msg = new ParameterizedMessage("{}", map);
        Log4jLogEvent event = Log4jLogEvent.newBuilder()
                .setLevel(Level.WARN)
                .setLoggerName(getClass().getName())
                .setMessage(msg)
                .setTimeMillis(0).build();

        for (int i = 0; i < 100; i++) {
            ((AsyncLoggerConfig)((org.apache.logging.log4j.core.Logger) log).get()).callAppenders(event);
            for (int j = 0; j < 3000; j++) {
                map.remove(String.valueOf(j));
            }
            for (int j = 0; j < 3000; j++) {
                map.put(String.valueOf(j), String.valueOf(System.nanoTime()));
            }
        }
    }

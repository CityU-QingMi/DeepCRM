    @Test
    public void testMissingRootLogger() throws Exception {
        final LoggerContext ctx = context.getLoggerContext();
        final Logger logger = ctx.getLogger("sample.Logger1");
        assertTrue("Logger should have the INFO level enabled", logger.isInfoEnabled());
        assertFalse("Logger should have the DEBUG level disabled", logger.isDebugEnabled());
        final Configuration config = ctx.getConfiguration();
        assertNotNull("Config not null", config);
//        final String MISSINGROOT = "MissingRootTest";
//        assertTrue("Incorrect Configuration. Expected " + MISSINGROOT + " but found " + config.getName(),
//                MISSINGROOT.equals(config.getName()));
        final Map<String, Appender> map = config.getAppenders();
        assertNotNull("Appenders not null", map);
        assertThat("There should only be two appenders", map, hasSize(2));
        assertThat(map, hasKey("List"));
        assertThat(map, hasKey("DefaultConsole-2"));

        final Map<String, LoggerConfig> loggerMap = config.getLoggers();
        assertNotNull("loggerMap not null", loggerMap);
        assertThat("There should only be one configured logger", loggerMap, hasSize(1));
        // only the sample logger, no root logger in loggerMap!
        assertThat("contains key=sample", loggerMap, hasKey("sample"));

        final LoggerConfig sample = loggerMap.get("sample");
        final Map<String, Appender> sampleAppenders = sample.getAppenders();
        assertThat("The sample logger should only have one appender", sampleAppenders, hasSize(1));
        // sample only has List appender, not Console!
        assertThat("The sample appender should be a ListAppender", sampleAppenders, hasKey("List"));
        assertThat(config, is(instanceOf(AbstractConfiguration.class)));
        final AbstractConfiguration baseConfig = (AbstractConfiguration) config;
        final LoggerConfig root = baseConfig.getRootLogger();
        final Map<String, Appender> rootAppenders = root.getAppenders();
        assertThat("The root logger should only have one appender", rootAppenders, hasSize(1));
        // root only has Console appender!
        assertThat("The root appender should be a ConsoleAppender", rootAppenders, hasKey("DefaultConsole-2"));
        assertEquals(Level.ERROR, root.getLevel());
    }

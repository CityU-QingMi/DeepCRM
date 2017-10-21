	private Layout<?> testFile(final String configResource) throws Exception {
		final Configuration configuration = getConfiguration(configResource);
		final FileAppender appender = configuration.getAppender("File");
		assertNotNull(appender);
		assertEquals("target/mylog.txt", appender.getFileName());
		//
		final LoggerConfig loggerConfig = configuration.getLoggerConfig("com.example.foo");
		assertNotNull(loggerConfig);
		assertEquals(Level.DEBUG, loggerConfig.getLevel());
		configuration.start();
		configuration.stop();
		return appender.getLayout();
	}

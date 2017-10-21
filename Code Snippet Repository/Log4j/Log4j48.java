	@Test
	public void testSystemProperties2() throws Exception {
		final Configuration configuration = getConfiguration("config-1.2/log4j-system-properties-2.properties");
		final RollingFileAppender appender = configuration.getAppender("RFA");
		assertEquals("${java.io.tmpdir}/hadoop.log", appender.getFileName());
		appender.stop(10, TimeUnit.SECONDS);
		Path path = new File(appender.getFileName()).toPath();
        Files.deleteIfExists(path);
        path = new File("${java.io.tmpdir}").toPath();
        Files.deleteIfExists(path);
	}

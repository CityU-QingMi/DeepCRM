	private void testRollingFileAppender(final String configResource, final String name, final String filePattern) throws URISyntaxException {
		final Configuration configuration = getConfiguration(configResource);
		final Appender appender = configuration.getAppender(name);
		assertNotNull(appender);
		assertEquals(name, appender.getName());
		assertTrue(appender.getClass().getName(), appender instanceof RollingFileAppender);
		final RollingFileAppender rfa = (RollingFileAppender) appender;
		assertEquals("target/hadoop.log", rfa.getFileName());
		assertEquals(filePattern, rfa.getFilePattern());
		final TriggeringPolicy triggeringPolicy = rfa.getTriggeringPolicy();
		assertNotNull(triggeringPolicy);
		assertTrue(triggeringPolicy.getClass().getName(), triggeringPolicy instanceof CompositeTriggeringPolicy);
		final CompositeTriggeringPolicy ctp = (CompositeTriggeringPolicy) triggeringPolicy;
		final TriggeringPolicy[] triggeringPolicies = ctp.getTriggeringPolicies();
		assertEquals(1, triggeringPolicies.length);
		final TriggeringPolicy tp = triggeringPolicies[0];
		assertTrue(tp.getClass().getName(), tp instanceof SizeBasedTriggeringPolicy);
		final SizeBasedTriggeringPolicy sbtp = (SizeBasedTriggeringPolicy) tp;
		assertEquals(256 * 1024 * 1024, sbtp.getMaxFileSize());
		final RolloverStrategy rolloverStrategy = rfa.getManager().getRolloverStrategy();
		assertTrue(rolloverStrategy.getClass().getName(), rolloverStrategy instanceof DefaultRolloverStrategy);
		final DefaultRolloverStrategy drs = (DefaultRolloverStrategy) rolloverStrategy;
		assertEquals(20, drs.getMaxIndex());
		configuration.start();
		configuration.stop();
	}

	@Test
	void logsWarningOnNonFilterableRunner(LogRecordListener listener) {
		Class<?> testClass = IgnoredJUnit4TestCase.class;
		RunnerTestDescriptorAwareFilter filter = adapter(
			matchMethodDescription(createTestDescription(testClass, "test")));

		resolve(new TestClassRequest(testClass, asList(filter)));

		// @formatter:off
		assertThat(listener.getLogRecords(TestClassRequestResolver.class, Level.WARNING)
			.map(LogRecord::getMessage)
			.filter(m -> m.equals("Runner " + IgnoredClassRunner.class.getName() //
				+ " (used on " + testClass.getName() + ") does not support filtering" //
				+ " and will therefore be run completely."))
			.count()
		).isEqualTo(1);
		// @formatter:on
	}

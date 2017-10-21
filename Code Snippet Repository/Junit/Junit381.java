	@Test
	void testAndRepeatedTest(LogRecordListener listener) {
		discoverTests(request().selectors(selectClass(TestCase.class)).build());

		// @formatter:off
		assertThat(listener.getLogRecords()
			.filter(logRecord -> logRecord.getLevel() == Level.WARNING)
			.map(LogRecord::getMessage)
			.filter(m -> m.matches("Possible configuration error: method .+ resulted in multiple TestDescriptors .+"))
			.count()
		).isEqualTo(1);
		// @formatter:on
	}

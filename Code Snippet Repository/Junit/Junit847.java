	@Test
	void returnsDisplayNameWhenUniqueIdCannotBeRead(LogRecordListener listener) {
		Description description = createTestDescription("ClassName", "methodName", "uniqueId");
		assertEquals("methodName(ClassName)", description.getDisplayName());

		Serializable uniqueId = new UniqueIdReader("wrongFieldName").apply(description);

		assertEquals(description.getDisplayName(), uniqueId);

		// @formatter:off
		assertThat(listener.getLogRecords(UniqueIdReader.class, Level.WARNING)
			.map(LogRecord::getMessage)
			.filter(m -> m.equals("Could not read unique ID for Description; using display name instead: "
					+ description.getDisplayName()))
			.count()
		).isEqualTo(1);
		// @formatter:on
	}

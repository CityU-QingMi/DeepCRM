	@Test
	void reportingEntryPublished() {
		StringWriter stringWriter = new StringWriter();
		listener(stringWriter).reportingEntryPublished(newTestIdentifier(), ReportEntry.from("foo", "bar"));
		String[] lines = lines(stringWriter);

		assertEquals(2, lines.length);
		assertAll("lines in the output", //
			() -> assertEquals("Reported:    demo-test ([engine:demo-engine])", lines[0]), //
			() -> assertTrue(lines[1].startsWith(INDENTATION + "=> Reported values: ReportEntry [timestamp =")), //
			() -> assertTrue(lines[1].endsWith(", foo = 'bar']")));
	}

	@Test
	void executionSkipped() {
		StringWriter stringWriter = new StringWriter();
		listener(stringWriter).executionSkipped(newTestIdentifier(), "Test" + EOL + "disabled");
		String[] lines = lines(stringWriter);

		assertEquals(3, lines.length);
		assertAll("lines in the output", //
			() -> assertEquals("Skipped:     demo-test ([engine:demo-engine])", lines[0]), //
			() -> assertEquals(INDENTATION + "=> Reason: Test", lines[1]), //
			() -> assertEquals(INDENTATION + "disabled", lines[2]));
	}

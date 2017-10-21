	@Test
	void executionFinishedWithFailure() {
		StringWriter stringWriter = new StringWriter();
		listener(stringWriter).executionFinished(newTestIdentifier(), failed(new AssertionError("Boom!")));
		String[] lines = lines(stringWriter);

		assertAll("lines in the output", //
			() -> assertEquals("Finished:    demo-test ([engine:demo-engine])", lines[0]), //
			() -> assertEquals(INDENTATION + "=> Exception: java.lang.AssertionError: Boom!", lines[1]));
	}

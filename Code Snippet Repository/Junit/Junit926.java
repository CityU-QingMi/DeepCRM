	@Test
	void parseValidXmlReportsDirs() {
		Path dir = Paths.get("build", "test-results");
		// @formatter:off
		assertAll(
			() -> assertEquals(Optional.of(dir), parseArgLine("--reports-dir build/test-results").getReportsDir()),
			() -> assertEquals(Optional.of(dir), parseArgLine("--reports-dir=build/test-results").getReportsDir())
		);
		// @formatter:on
	}

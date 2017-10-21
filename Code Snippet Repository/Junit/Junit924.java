	@Test
	void parseValidExcludedEngines() {
		// @formatter:off
		assertAll(
			() -> assertEquals(asList("junit-jupiter"), parseArgLine("-E junit-jupiter").getExcludedEngines()),
			() -> assertEquals(asList("junit-vintage"), parseArgLine("--exclude-engine junit-vintage").getExcludedEngines()),
			() -> assertEquals(emptyList(), parseArgLine("").getExcludedEngines())
		);
		// @formatter:on
	}

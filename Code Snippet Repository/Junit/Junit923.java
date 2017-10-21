	@Test
	void parseValidIncludedEngines() {
		// @formatter:off
		assertAll(
			() -> assertEquals(asList("junit-jupiter"), parseArgLine("-e junit-jupiter").getIncludedEngines()),
			() -> assertEquals(asList("junit-vintage"), parseArgLine("--include-engine junit-vintage").getIncludedEngines()),
			() -> assertEquals(emptyList(), parseArgLine("").getIncludedEngines())
		);
		// @formatter:on
	}

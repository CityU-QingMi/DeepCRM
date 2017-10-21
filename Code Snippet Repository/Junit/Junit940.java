	@Test
	void parseValidIncludeClassNamePatterns() {
		// @formatter:off
		assertAll(
			() -> assertEquals(singletonList(".*Test"), parseArgLine("-n .*Test").getIncludedClassNamePatterns()),
			() -> assertEquals(asList(".*Test", ".*Tests"), parseArgLine("--include-classname .*Test --include-classname .*Tests").getIncludedClassNamePatterns()),
			() -> assertEquals(singletonList(".*Test"), parseArgLine("--include-classname=.*Test").getIncludedClassNamePatterns())
		);
		// @formatter:on
	}

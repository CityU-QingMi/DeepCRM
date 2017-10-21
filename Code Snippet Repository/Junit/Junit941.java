	@Test
	void parseValidExcludeClassNamePatterns() {
		// @formatter:off
		assertAll(
			() -> assertEquals(singletonList(".*Test"), parseArgLine("-N .*Test").getExcludedClassNamePatterns()),
			() -> assertEquals(asList(".*Test", ".*Tests"), parseArgLine("--exclude-classname .*Test --exclude-classname .*Tests").getExcludedClassNamePatterns()),
			() -> assertEquals(singletonList(".*Test"), parseArgLine("--exclude-classname=.*Test").getExcludedClassNamePatterns())
		);
		// @formatter:on
	}

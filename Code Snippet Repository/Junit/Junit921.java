	@Test
	void parseNoArguments() {
		String[] noArguments = {};
		CommandLineOptions options = createParser().parse(noArguments);

		// @formatter:off
		assertAll(
			() -> assertFalse(options.isAnsiColorOutputDisabled()),
			() -> assertFalse(options.isDisplayHelp()),
			() -> assertEquals(CommandLineOptions.DEFAULT_DETAILS, options.getDetails()),
			() -> assertFalse(options.isScanClasspath()),
			() -> assertEquals(singletonList(STANDARD_INCLUDE_PATTERN), options.getIncludedClassNamePatterns()),
			() -> assertEquals(emptyList(), options.getExcludedClassNamePatterns()),
			() -> assertEquals(emptyList(), options.getIncludedPackages()),
			() -> assertEquals(emptyList(), options.getExcludedPackages()),
			() -> assertEquals(emptyList(), options.getIncludedTags()),
			() -> assertEquals(emptyList(), options.getExcludedTags()),
			() -> assertEquals(emptyList(), options.getAdditionalClasspathEntries()),
			() -> assertEquals(Optional.empty(), options.getReportsDir()),
			() -> assertEquals(emptyList(), options.getSelectedUris()),
			() -> assertEquals(emptyList(), options.getSelectedFiles()),
			() -> assertEquals(emptyList(), options.getSelectedDirectories()),
			() -> assertEquals(emptyList(), options.getSelectedClasspathEntries()),
			() -> assertEquals(emptyMap(), options.getConfigurationParameters())
		);
		// @formatter:on
	}

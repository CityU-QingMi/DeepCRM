	@Test
	void resolvesApplyingClassNameFilters() throws Exception {
		Path root = getClasspathRoot(PlainJUnit4TestCaseWithSingleTestWhichFails.class);

		LauncherDiscoveryRequest discoveryRequest = request().selectors(selectClasspathRoots(singleton(root))).filters(
			includeClassNamePatterns(".*JUnit4.*"), includeClassNamePatterns(".*Plain.*")).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		// @formatter:off
		assertThat(engineDescriptor.getChildren())
			.extracting(TestDescriptor::getDisplayName)
			.contains(PlainJUnit4TestCaseWithSingleTestWhichFails.class.getName())
			.doesNotContain(JUnit4TestCaseWithOverloadedMethod.class.getName())
			.doesNotContain(PlainJUnit3TestCaseWithSingleTestWhichFails.class.getName());
		// @formatter:on
	}

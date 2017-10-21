	@Test
	void resolvesApplyingPackageNameFilters() throws Exception {
		Path root = getClasspathRoot(PlainJUnit4TestCaseWithSingleTestWhichFails.class);

		LauncherDiscoveryRequest discoveryRequest = request().selectors(selectClasspathRoots(singleton(root))).filters(
			includePackageNames("org"), includePackageNames("org.junit")).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		// @formatter:off
		assertThat(engineDescriptor.getChildren())
				.extracting(TestDescriptor::getDisplayName)
				.contains(PlainJUnit4TestCaseWithSingleTestWhichFails.class.getName());
		// @formatter:on
	}

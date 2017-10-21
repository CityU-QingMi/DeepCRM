	@Test
	void resolvesClasspathSelector() throws Exception {
		Path root = getClasspathRoot(PlainJUnit4TestCaseWithSingleTestWhichFails.class);
		LauncherDiscoveryRequest discoveryRequest = request().selectors(selectClasspathRoots(singleton(root))).build();
		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		// @formatter:off
		assertThat(engineDescriptor.getChildren())
			.extracting(TestDescriptor::getDisplayName)
			.contains(PlainJUnit4TestCaseWithSingleTestWhichFails.class.getName())
			.contains(PlainJUnit3TestCaseWithSingleTestWhichFails.class.getName())
			.doesNotContain(PlainOldJavaClassWithoutAnyTest.class.getName());
		// @formatter:on
	}

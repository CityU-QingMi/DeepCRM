	@Test
	void resolvesPackageSelectorForJUnit3SamplesPackage() {
		Class<?> testClass = PlainJUnit3TestCaseWithSingleTestWhichFails.class;

		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectPackage(testClass.getPackage().getName())).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		// @formatter:off
		assertThat(engineDescriptor.getChildren())
			.extracting(TestDescriptor::getDisplayName)
			.contains(testClass.getName())
			.doesNotContain(PlainJUnit4TestCaseWithSingleTestWhichFails.class.getName());
		// @formatter:on
	}

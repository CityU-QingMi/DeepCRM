	@Test
	void resolvesPackageSelectorForJUnit4SamplesPackage() {
		Class<?> testClass = PlainJUnit4TestCaseWithSingleTestWhichFails.class;

		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectPackage(testClass.getPackage().getName())).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		// @formatter:off
		assertThat(engineDescriptor.getChildren())
			.extracting(TestDescriptor::getDisplayName)
			.contains(testClass.getName())
			.doesNotContain(PlainJUnit3TestCaseWithSingleTestWhichFails.class.getName());
		// @formatter:on
	}

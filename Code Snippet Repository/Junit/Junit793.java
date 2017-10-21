	@Test
	void doesNotResolveMissingUniqueIdSelectorForSingleClass() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForClass(testClass) + "/[test:doesNotExist]")).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor testDescriptor = getOnlyElement(runnerDescriptor.getChildren());
		assertInitializationError(testDescriptor, Filter.class, testClass);
	}

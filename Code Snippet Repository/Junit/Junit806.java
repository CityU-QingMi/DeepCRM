	@Test
	void resolvesJUnit3TestCase() throws Exception {
		Class<?> testClass = PlainJUnit3TestCaseWithSingleTestWhichFails.class;
		LauncherDiscoveryRequest discoveryRequest = discoveryRequestForClass(testClass);

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor childDescriptor = getOnlyElement(runnerDescriptor.getChildren());
		assertTestMethodDescriptor(childDescriptor, testClass, "test",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}

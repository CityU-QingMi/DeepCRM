	@Test
	void resolvesJUnit4TestClassWithCustomRunner() throws Exception {
		Class<?> testClass = SingleFailingTheoryTestCase.class;
		LauncherDiscoveryRequest discoveryRequest = discoveryRequestForClass(testClass);

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor childDescriptor = getOnlyElement(runnerDescriptor.getChildren());
		assertTestMethodDescriptor(childDescriptor, testClass, "theory",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}

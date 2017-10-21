	@Test
	void resolvesUniqueIdSelectorForSingleMethod() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "failingTest"))).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor childDescriptor = getOnlyElement(runnerDescriptor.getChildren());
		assertTestMethodDescriptor(childDescriptor, testClass, "failingTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}

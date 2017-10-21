	@Test
	void resolvesUniqueIdSelectorOfSingleClassWithinSuite() throws Exception {
		Class<?> suiteClass = JUnit4SuiteWithTwoTestCases.class;
		Class<?> testClass = PlainJUnit4TestCaseWithSingleTestWhichFails.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForClasses(suiteClass, testClass))).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor suiteDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(suiteDescriptor, suiteClass);

		TestDescriptor testClassDescriptor = getOnlyElement(suiteDescriptor.getChildren());
		assertContainerTestDescriptor(testClassDescriptor, suiteClass, testClass);

		TestDescriptor testMethodDescriptor = getOnlyElement(testClassDescriptor.getChildren());
		assertTestMethodDescriptor(testMethodDescriptor, testClass, "failingTest",
			VintageUniqueIdBuilder.uniqueIdForClasses(suiteClass, testClass));
	}

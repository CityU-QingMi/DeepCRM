	@Test
	void resolvesUniqueIdSelectorOfSingleMethodWithinSuite() throws Exception {
		Class<?> suiteClass = JUnit4SuiteWithTwoTestCases.class;
		Class<?> testClass = PlainJUnit4TestCaseWithTwoTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors(selectUniqueId(
			VintageUniqueIdBuilder.uniqueIdForMethod(VintageUniqueIdBuilder.uniqueIdForClasses(suiteClass, testClass),
				testClass, "successfulTest"))).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor suiteDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(suiteDescriptor, suiteClass);

		TestDescriptor testClassDescriptor = getOnlyElement(suiteDescriptor.getChildren());
		assertContainerTestDescriptor(testClassDescriptor, suiteClass, testClass);

		TestDescriptor testMethodDescriptor = getOnlyElement(testClassDescriptor.getChildren());
		assertTestMethodDescriptor(testMethodDescriptor, testClass, "successfulTest",
			VintageUniqueIdBuilder.uniqueIdForClasses(suiteClass, testClass));
	}

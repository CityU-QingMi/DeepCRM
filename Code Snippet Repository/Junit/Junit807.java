	@Test
	void resolvesJUnit3SuiteWithSingleTestCaseWithSingleTestWhichFails() throws Exception {
		Class<?> suiteClass = JUnit3SuiteWithSingleTestCaseWithSingleTestWhichFails.class;
		Class<?> testClass = PlainJUnit3TestCaseWithSingleTestWhichFails.class;
		LauncherDiscoveryRequest discoveryRequest = discoveryRequestForClass(suiteClass);

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor suiteDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(suiteDescriptor, suiteClass);

		TestDescriptor testClassDescriptor = getOnlyElement(suiteDescriptor.getChildren());
		assertContainerTestDescriptor(testClassDescriptor, suiteClass, testClass);

		TestDescriptor testMethodDescriptor = getOnlyElement(testClassDescriptor.getChildren());
		assertTestMethodDescriptor(testMethodDescriptor, testClass, "test",
			VintageUniqueIdBuilder.uniqueIdForClasses(suiteClass, testClass));
	}

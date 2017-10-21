	@Test
	void ignoresRedundantSelector() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors( //
			selectMethod(testClass, testClass.getMethod("failingTest")), //
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "failingTest") //
			)).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor testMethodDescriptor = getOnlyElement(runnerDescriptor.getChildren());
		assertTestMethodDescriptor(testMethodDescriptor, testClass, "failingTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}

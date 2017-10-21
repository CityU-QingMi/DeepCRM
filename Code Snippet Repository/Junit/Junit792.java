	@Test
	void resolvesMultipleUniqueIdSelectorsForMethodsOfSameClass() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithTwoTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors(
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "successfulTest")),
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "failingTest"))).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		List<TestDescriptor> testMethodDescriptors = new ArrayList<>(runnerDescriptor.getChildren());
		assertThat(testMethodDescriptors).hasSize(2);
		assertTestMethodDescriptor(testMethodDescriptors.get(0), testClass, "failingTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
		assertTestMethodDescriptor(testMethodDescriptors.get(1), testClass, "successfulTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}

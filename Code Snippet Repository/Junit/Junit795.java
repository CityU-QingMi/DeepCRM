	@Test
	void resolvesCombinationOfMethodAndUniqueIdSelector() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors( //
			selectMethod(testClass, testClass.getMethod("failingTest")), //
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "abortedTest") //
			)).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		List<TestDescriptor> testMethodDescriptors = new ArrayList<>(runnerDescriptor.getChildren());
		assertThat(testMethodDescriptors).hasSize(2);
		assertTestMethodDescriptor(testMethodDescriptors.get(0), testClass, "abortedTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
		assertTestMethodDescriptor(testMethodDescriptors.get(1), testClass, "failingTest",
			VintageUniqueIdBuilder.uniqueIdForClass(testClass));
	}

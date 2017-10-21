	@Test
	void ignoresMoreFineGrainedSelectorsWhenClassIsSelectedAsWell() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;
		LauncherDiscoveryRequest discoveryRequest = request().selectors( //
			selectMethod(testClass, testClass.getMethod("failingTest")), //
			selectUniqueId(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "abortedTest")), selectClass(testClass) //
		).build();

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		assertThat(runnerDescriptor.getChildren()).hasSize(5);
	}

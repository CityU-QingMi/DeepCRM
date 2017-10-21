	@Test
	void resolvesJUnit4TestCaseWithOverloadedMethod() {
		Class<?> testClass = JUnit4TestCaseWithOverloadedMethod.class;
		LauncherDiscoveryRequest discoveryRequest = discoveryRequestForClass(testClass);

		TestDescriptor engineDescriptor = discoverTests(discoveryRequest);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		List<TestDescriptor> testMethodDescriptors = new ArrayList<>(runnerDescriptor.getChildren());
		assertThat(testMethodDescriptors).hasSize(2);

		TestDescriptor testMethodDescriptor = testMethodDescriptors.get(0);
		assertEquals("theory", testMethodDescriptor.getDisplayName());
		Assertions.assertEquals(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "theory", "0"),
			testMethodDescriptor.getUniqueId());
		assertClassSource(testClass, testMethodDescriptor);

		testMethodDescriptor = testMethodDescriptors.get(1);
		assertEquals("theory", testMethodDescriptor.getDisplayName());
		Assertions.assertEquals(VintageUniqueIdBuilder.uniqueIdForMethod(testClass, "theory", "1"),
			testMethodDescriptor.getUniqueId());
		assertClassSource(testClass, testMethodDescriptor);
	}

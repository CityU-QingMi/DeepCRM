	@Test
	void resolvesTestSourceForParameterizedTests() throws Exception {
		Class<?> testClass = ParameterizedTestCase.class;
		LauncherDiscoveryRequest request = request().selectors(selectClass(testClass)).build();

		TestDescriptor engineDescriptor = discoverTests(request);

		TestDescriptor runnerDescriptor = getOnlyElement(engineDescriptor.getChildren());
		assertRunnerTestDescriptor(runnerDescriptor, testClass);

		TestDescriptor fooParentDescriptor = findChildByDisplayName(runnerDescriptor, "[foo]");
		assertTrue(fooParentDescriptor.isContainer());
		assertFalse(fooParentDescriptor.isTest());
		assertThat(fooParentDescriptor.getSource()).isEmpty();

		TestDescriptor testMethodDescriptor = getOnlyElement(fooParentDescriptor.getChildren());
		assertEquals("test[foo]", testMethodDescriptor.getDisplayName());
		assertTrue(testMethodDescriptor.isTest());
		assertFalse(testMethodDescriptor.isContainer());
		assertMethodSource(testClass.getMethod("test"), testMethodDescriptor);
	}

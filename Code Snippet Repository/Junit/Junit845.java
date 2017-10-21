	@Test
	void registersDynamicTestDescriptors() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithSingleTestWhichFails.class;
		UniqueId runnerId = engineId().append(SEGMENT_TYPE_RUNNER, testClass.getName());
		RunnerTestDescriptor runnerTestDescriptor = new RunnerTestDescriptor(runnerId, testClass,
			new BlockJUnit4ClassRunner(testClass));
		UniqueId dynamicTestId = runnerId.append(SEGMENT_TYPE_DYNAMIC, "dynamicTest");
		Description dynamicDescription = createTestDescription(testClass, "dynamicTest");
		VintageTestDescriptor dynamicTestDescriptor = new VintageTestDescriptor(dynamicTestId, dynamicDescription);

		TestRun testRun = new TestRun(runnerTestDescriptor);
		testRun.registerDynamicTest(dynamicTestDescriptor);

		assertThat(testRun.lookupTestDescriptor(dynamicDescription)).contains(dynamicTestDescriptor);
		assertTrue(testRun.isDescendantOfRunnerTestDescriptor(dynamicTestDescriptor));
	}

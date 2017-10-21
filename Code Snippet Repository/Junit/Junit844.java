	@Test
	void returnsEmptyOptionalForUnknownDescriptions() throws Exception {
		Class<?> testClass = PlainJUnit4TestCaseWithSingleTestWhichFails.class;
		UniqueId runnerId = engineId().append(SEGMENT_TYPE_RUNNER, testClass.getName());
		RunnerTestDescriptor runnerTestDescriptor = new RunnerTestDescriptor(runnerId, testClass,
			new BlockJUnit4ClassRunner(testClass));
		Description unknownDescription = createTestDescription(testClass, "dynamicTest");

		TestRun testRun = new TestRun(runnerTestDescriptor);
		Optional<VintageTestDescriptor> testDescriptor = testRun.lookupTestDescriptor(unknownDescription);

		assertThat(testDescriptor).isEmpty();
	}

	@Test
	void canGetListOfFailures() {
		RuntimeException failedException = new RuntimeException("Pow!");
		TestDescriptorStub testDescriptor = new TestDescriptorStub(UniqueId.root("root", "1"), "failingTest") {

			@Override
			public Optional<TestSource> getSource() {
				return Optional.of(ClassSource.from(Object.class));
			}
		};
		TestIdentifier failingTest = TestIdentifier.from(testDescriptor);
		listener.testPlanExecutionStarted(testPlan);
		listener.executionStarted(failingTest);
		listener.executionFinished(failingTest, TestExecutionResult.failed(failedException));
		listener.testPlanExecutionFinished(testPlan);
		final List<TestExecutionSummary.Failure> failures = listener.getSummary().getFailures();
		assertThat(failures).hasSize(1);
		assertThat(failures.get(0).getException()).isEqualTo(failedException);
		assertThat(failures.get(0).getTestIdentifier()).isEqualTo(failingTest);
	}

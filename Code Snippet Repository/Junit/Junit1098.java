	@Test
	void reportingCorrectFailures() throws Exception {
		RuntimeException failedException = new RuntimeException("failed");

		TestDescriptorStub testDescriptor = new TestDescriptorStub(UniqueId.root("root", "2"), "failingTest") {

			@Override
			public Optional<TestSource> getSource() {
				return Optional.of(ClassSource.from(Object.class));
			}
		};
		TestIdentifier failed = TestIdentifier.from(testDescriptor);
		TestIdentifier aborted = TestIdentifier.from(new TestDescriptorStub(UniqueId.root("root", "3"), "abortedTest"));

		listener.testPlanExecutionStarted(testPlan);
		listener.executionStarted(failed);
		listener.executionFinished(failed, TestExecutionResult.failed(failedException));
		listener.executionStarted(aborted);
		listener.executionFinished(aborted, TestExecutionResult.aborted(new RuntimeException("aborted")));
		listener.testPlanExecutionFinished(testPlan);

		// An aborted test is not a failure
		assertEquals(1, listener.getSummary().getTestsFailedCount());

		String failuresString = failuresAsString();
		assertAll("failures", //
			() -> assertTrue(failuresString.contains("Failures (1)"), "test failures"), //
			() -> assertTrue(failuresString.contains(Object.class.getName()), "source"), //
			() -> assertTrue(failuresString.contains("failingTest"), "display name"), //
			() -> assertTrue(failuresString.contains("=> " + failedException), "exception") //
		);
	}

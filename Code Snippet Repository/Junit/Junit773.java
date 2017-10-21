	TestExecutionResult getStoredResultOrSuccessful(TestDescriptor testDescriptor) {
		List<TestExecutionResult> testExecutionResults = executionResults.get(testDescriptor);

		if (testExecutionResults == null) {
			return successful();
		}
		if (testExecutionResults.size() == 1) {
			return testExecutionResults.get(0);
		}
		// @formatter:off
		List<Throwable> failures = testExecutionResults
				.stream()
				.map(TestExecutionResult::getThrowable)
				.map(Optional::get)
				.collect(toList());
		// @formatter:on
		return failed(new MultipleFailuresError("", failures));
	}

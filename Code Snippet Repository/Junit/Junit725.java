	@Override
	public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
		Description description = findJUnit4Description(testIdentifier);
		Status status = testExecutionResult.getStatus();
		if (status == ABORTED) {
			this.notifier.fireTestAssumptionFailed(toFailure(testExecutionResult, description));
		}
		else if (status == FAILED) {
			this.notifier.fireTestFailure(toFailure(testExecutionResult, description));
		}
		if (description.isTest()) {
			this.notifier.fireTestFinished(description);
		}
	}

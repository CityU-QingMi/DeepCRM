	@Override
	public void testRunFinished(Result result) {
		RunnerTestDescriptor runnerTestDescriptor = testRun.getRunnerTestDescriptor();
		if (testRun.isNotSkipped(runnerTestDescriptor)) {
			if (testRun.isNotStarted(runnerTestDescriptor)) {
				fireExecutionStarted(runnerTestDescriptor);
			}
			if (testRun.isNotFinished(runnerTestDescriptor)) {
				fireExecutionFinished(runnerTestDescriptor);
			}
		}
	}

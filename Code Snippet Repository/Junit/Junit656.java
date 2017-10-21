	@Override
	public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
		if (testIdentifier.isContainer()) {
			Long creationMillis = frames.pop();
			printVerticals(theme.end());
			printf(Color.CONTAINER, " %s", testIdentifier.getDisplayName());
			printf(NONE, " finished after %d ms.%n", System.currentTimeMillis() - creationMillis);
			return;
		}
		testExecutionResult.getThrowable().ifPresent(t -> printDetail(Color.FAILED, "caught", readStackTrace(t)));
		printDetail(NONE, "duration", "%d ms%n", System.currentTimeMillis() - executionStartedMillis);
		String status = theme.status(testExecutionResult) + " " + testExecutionResult.getStatus();
		printDetail(Color.valueOf(testExecutionResult), "status", "%s%n", status);
	}

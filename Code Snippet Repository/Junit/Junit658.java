	void markFinished(TestIdentifier testIdentifier, TestExecutionResult result) {
		this.endInstants.put(testIdentifier, this.clock.instant());
		if (result.getStatus() == ABORTED) {
			String reason = result.getThrowable().map(ExceptionUtils::readStackTrace).orElse("");
			this.skippedTests.put(testIdentifier, reason);
		}
		else {
			this.finishedTests.put(testIdentifier, result);
		}
	}

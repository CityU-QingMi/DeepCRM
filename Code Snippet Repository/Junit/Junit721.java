	@Override
	public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {

		switch (testExecutionResult.getStatus()) {

			case SUCCESSFUL: {
				if (testIdentifier.isContainer()) {
					this.summary.containersSucceeded.incrementAndGet();
				}
				if (testIdentifier.isTest()) {
					this.summary.testsSucceeded.incrementAndGet();
				}
				break;
			}

			case ABORTED: {
				if (testIdentifier.isContainer()) {
					this.summary.containersAborted.incrementAndGet();
				}
				if (testIdentifier.isTest()) {
					this.summary.testsAborted.incrementAndGet();
				}
				break;
			}

			case FAILED: {
				if (testIdentifier.isContainer()) {
					this.summary.containersFailed.incrementAndGet();
				}
				if (testIdentifier.isTest()) {
					this.summary.testsFailed.incrementAndGet();
				}
				testExecutionResult.getThrowable().ifPresent(
					throwable -> this.summary.addFailure(testIdentifier, throwable));
				break;
			}

			default:
				throw new PreconditionViolationException(
					"Unsupported execution status:" + testExecutionResult.getStatus());
		}
	}

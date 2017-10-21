	public final String status(TestExecutionResult result) {
		switch (result.getStatus()) {
			case SUCCESSFUL:
				return successful();
			case ABORTED:
				return aborted();
			case FAILED:
				return failed();
			default:
				return result.getStatus().name();
		}
	}

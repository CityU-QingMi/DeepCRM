	static Color valueOf(TestExecutionResult result) {
		switch (result.getStatus()) {
			case SUCCESSFUL:
				return Color.SUCCESSFUL;
			case ABORTED:
				return Color.ABORTED;
			case FAILED:
				return Color.FAILED;
			default:
				return Color.NONE;
		}
	}

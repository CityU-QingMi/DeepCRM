	private void printThrowable(String indent, TestExecutionResult result) {
		if (!result.getThrowable().isPresent()) {
			return;
		}
		Throwable throwable = result.getThrowable().get();
		String message = throwable.getMessage();
		if (StringUtils.isBlank(message)) {
			message = throwable.toString();
		}
		printMessage(FAILED, indent, message);
	}

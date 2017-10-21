	public void afterTestExecution(Object testInstance, Method testMethod, @Nullable Throwable exception) throws Exception {
		String callbackName = "afterTestExecution";
		prepareForAfterCallback(callbackName, testInstance, testMethod, exception);

		Throwable afterTestExecutionException = null;
		// Traverse the TestExecutionListeners in reverse order to ensure proper
		// "wrapper"-style execution of listeners.
		for (TestExecutionListener testExecutionListener : getReversedTestExecutionListeners()) {
			try {
				testExecutionListener.afterTestExecution(getTestContext());
			}
			catch (Throwable ex) {
				logException(ex, callbackName, testExecutionListener, testInstance, testMethod);
				if (afterTestExecutionException == null) {
					afterTestExecutionException = ex;
				}
				else {
					afterTestExecutionException.addSuppressed(ex);
				}
			}
		}
		if (afterTestExecutionException != null) {
			ReflectionUtils.rethrowException(afterTestExecutionException);
		}
	}

	public void afterTestMethod(Object testInstance, Method testMethod, @Nullable Throwable exception) throws Exception {
		String callbackName = "afterTestMethod";
		prepareForAfterCallback(callbackName, testInstance, testMethod, exception);

		Throwable afterTestMethodException = null;
		// Traverse the TestExecutionListeners in reverse order to ensure proper
		// "wrapper"-style execution of listeners.
		for (TestExecutionListener testExecutionListener : getReversedTestExecutionListeners()) {
			try {
				testExecutionListener.afterTestMethod(getTestContext());
			}
			catch (Throwable ex) {
				logException(ex, callbackName, testExecutionListener, testInstance, testMethod);
				if (afterTestMethodException == null) {
					afterTestMethodException = ex;
				}
				else {
					afterTestMethodException.addSuppressed(ex);
				}
			}
		}
		if (afterTestMethodException != null) {
			ReflectionUtils.rethrowException(afterTestMethodException);
		}
	}

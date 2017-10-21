	public void beforeTestExecution(Object testInstance, Method testMethod) throws Exception {
		String callbackName = "beforeTestExecution";
		prepareForBeforeCallback(callbackName, testInstance, testMethod);

		for (TestExecutionListener testExecutionListener : getTestExecutionListeners()) {
			try {
				testExecutionListener.beforeTestExecution(getTestContext());
			}
			catch (Throwable ex) {
				handleBeforeException(ex, callbackName, testExecutionListener, testInstance, testMethod);
			}
		}
	}

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		Method testMethod = testResult.getMethod().getConstructorOrMethod().getMethod();
		boolean beforeCallbacksExecuted = false;

		try {
			this.testContextManager.beforeTestExecution(this, testMethod);
			beforeCallbacksExecuted = true;
		}
		catch (Throwable ex) {
			this.testException = ex;
		}

		if (beforeCallbacksExecuted) {
			callBack.runTestMethod(testResult);
			this.testException = getTestResultException(testResult);
		}

		try {
			this.testContextManager.afterTestExecution(this, testMethod, this.testException);
		}
		catch (Throwable ex) {
			if (this.testException == null) {
				this.testException = ex;
			}
		}

		if (this.testException != null) {
			throwAsUncheckedException(this.testException);
		}
	}

	private RunResult invokeAllTests(TestsToRun testsToRun) {
		RunResult runResult;
		ReporterFactory reporterFactory = parameters.getReporterFactory();
		try {
			RunListener runListener = reporterFactory.createReporter();
			launcher.registerTestExecutionListeners(new RunListenerAdapter(runListener));

			for (Class<?> testClass : testsToRun) {
				invokeSingleClass(testClass, runListener);
			}
		}
		finally {
			runResult = reporterFactory.close();
		}
		return runResult;
	}

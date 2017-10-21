	private void runTestAndAssertCounters(Class<?> testClass) {
		Launcher launcher = LauncherFactory.create();
		ExceptionTrackingListener listener = new ExceptionTrackingListener();
		launcher.registerTestExecutionListeners(listener);

		launcher.execute(request().selectors(selectClass(testClass)).build());
		TestExecutionSummary summary = listener.getSummary();

		String name = testClass.getSimpleName();
		int expectedStartedCount = getExpectedStartedCount(testClass);
		int expectedSucceededCount = getExpectedSucceededCount(testClass);
		int expectedFailedCount = getExpectedFailedCount(testClass);

		// @formatter:off
		assertAll(
			() -> assertEquals(1, summary.getTestsFoundCount(), () -> name + ": tests found"),
			() -> assertEquals(0, summary.getTestsSkippedCount(), () -> name + ": tests skipped"),
			() -> assertEquals(0, summary.getTestsAbortedCount(), () -> name + ": tests aborted"),
			() -> assertEquals(expectedStartedCount, summary.getTestsStartedCount(), () -> name + ": tests started"),
			() -> assertEquals(expectedSucceededCount, summary.getTestsSucceededCount(), () -> name + ": tests succeeded"),
			() -> assertEquals(expectedFailedCount, summary.getTestsFailedCount(), () -> name + ": tests failed")
		);
		// @formatter:on

		// Ensure it was an AssertionFailedError that failed the test and not
		// something else like an error in the @Configuration class, etc.
		if (expectedFailedCount > 0) {
			assertEquals(1, listener.exceptions.size(), "exceptions expected");
			Throwable exception = listener.exceptions.get(0);
			if (!(exception instanceof AssertionFailedError)) {
				throw new AssertionFailedError(
					exception.getClass().getName() + " is not an instance of " + AssertionFailedError.class.getName(),
					exception);
			}
		}
	}

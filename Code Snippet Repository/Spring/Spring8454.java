	public static void runTestsAndAssertCounters(Computer computer, int expectedStartedCount, int expectedFailedCount,
			int expectedFinishedCount, int expectedIgnoredCount, int expectedAssumptionFailedCount,
			Class<?>... testClasses) throws Exception {

		JUnitCore junit = new JUnitCore();
		TrackingRunListener listener = new TrackingRunListener();
		junit.addListener(listener);
		junit.run(computer, testClasses);

		// @formatter:off
		assertAll(
			() -> assertEquals(expectedStartedCount, listener.getTestStartedCount(), "tests started"),
			() -> assertEquals(expectedFailedCount, listener.getTestFailureCount(), "tests failed"),
			() -> assertEquals(expectedFinishedCount, listener.getTestFinishedCount(), "tests finished"),
			() -> assertEquals(expectedIgnoredCount, listener.getTestIgnoredCount(), "tests ignored"),
			() -> assertEquals(expectedAssumptionFailedCount, listener.getTestAssumptionFailureCount(), "failed assumptions")
		);
		// @formatter:on
	}

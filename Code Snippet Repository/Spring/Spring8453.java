	public static void runTestsAndAssertCounters(Class<? extends Runner> runnerClass, Class<?> testClass,
			int expectedStartedCount, int expectedFailedCount, int expectedFinishedCount, int expectedIgnoredCount,
			int expectedAssumptionFailedCount) throws Exception {

		TrackingRunListener listener = new TrackingRunListener();

		if (runnerClass != null) {
			Constructor<?> constructor = runnerClass.getConstructor(Class.class);
			Runner runner = (Runner) BeanUtils.instantiateClass(constructor, testClass);
			RunNotifier notifier = new RunNotifier();
			notifier.addListener(listener);
			runner.run(notifier);
		}
		else {
			JUnitCore junit = new JUnitCore();
			junit.addListener(listener);
			junit.run(testClass);
		}

		// @formatter:off
		assertAll(
			() -> assertEquals(expectedStartedCount, listener.getTestStartedCount(), "tests started for [" + testClass + "]"),
			() -> assertEquals(expectedFailedCount, listener.getTestFailureCount(), "tests failed for [" + testClass + "]"),
			() -> assertEquals(expectedFinishedCount, listener.getTestFinishedCount(), "tests finished for [" + testClass + "]"),
			() -> assertEquals(expectedIgnoredCount, listener.getTestIgnoredCount(), "tests ignored for [" + testClass + "]"),
			() -> assertEquals(expectedAssumptionFailedCount, listener.getTestAssumptionFailureCount(), "failed assumptions for [" + testClass + "]")
		);
		// @formatter:on
	}

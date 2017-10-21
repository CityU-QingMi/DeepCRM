	private void runTestClassAndAssertStats(Class<?> testClass, int expectedTestCount) {
		final int expectedTestFailureCount = 0;
		final int expectedTestStartedCount = expectedTestCount;
		final int expectedTestFinishedCount = expectedTestCount;

		final TrackingTestNGTestListener listener = new TrackingTestNGTestListener();
		final TestNG testNG = new TestNG();
		testNG.addListener((ITestNGListener) listener);
		testNG.setTestClasses(new Class<?>[] { testClass });
		testNG.setVerbose(0);
		testNG.run();

		assertEquals("Failures for test class [" + testClass + "].", expectedTestFailureCount,
			listener.testFailureCount);
		assertEquals("Tests started for test class [" + testClass + "].", expectedTestStartedCount,
			listener.testStartCount);
		assertEquals("Successful tests for test class [" + testClass + "].", expectedTestFinishedCount,
			listener.testSuccessCount);
	}

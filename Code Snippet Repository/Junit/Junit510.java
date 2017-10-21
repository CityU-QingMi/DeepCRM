	private void assertBeforeAllAndAfterAllCallbacks(Class<?> testClass, int testsStarted, int testsSuccessful,
			String... expectedCalls) {

		callSequence.clear();
		LauncherDiscoveryRequest request = request().selectors(selectClass(testClass)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(testsStarted, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(testsSuccessful, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");

		assertEquals(asList(expectedCalls), callSequence, () -> "wrong call sequence for " + testClass.getName());
	}

	@Test
	void beforeEachMethodThrowsAnException() {
		LauncherDiscoveryRequest request = request().selectors(
			selectClass(ExceptionInBeforeEachMethodTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(0, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		assertEquals(asList(
			"beforeEachMethod", // throws an exception.
				// fooBeforeTestExecutionCallback should not get invoked.
					// test should not get invoked.
				// fooAfterTestExecutionCallback should not get invoked.
			"afterEachMethod"
		), callSequence, "wrong call sequence");
		// @formatter:on

		assertNull(actualExceptionInAfterTestExecution,
			"test exception (fooAfterTestExecutionCallback should not have been called)");
	}

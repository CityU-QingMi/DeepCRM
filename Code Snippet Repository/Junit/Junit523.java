	@Test
	void beforeTestExecutionCallbackThrowsAnException() {
		LauncherDiscoveryRequest request = request().selectors(
			selectClass(ExceptionInBeforeTestExecutionCallbackTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(0, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		assertEquals(asList(
			"beforeEachMethod",
				"fooBeforeTestExecutionCallback",
				"exceptionThrowingBeforeTestExecutionCallback", // throws an exception.
				// barBeforeTestExecutionCallback should not get invoked.
					// test() should not get invoked.
				"barAfterTestExecutionCallback",
				"fooAfterTestExecutionCallback",
			"afterEachMethod"
		), callSequence, "wrong call sequence");
		// @formatter:on

		assertNotNull(actualExceptionInAfterTestExecution, "test exception");
		assertTrue(actualExceptionInAfterTestExecution.isPresent(), "test exception should be present");
		assertEquals(EnigmaException.class, actualExceptionInAfterTestExecution.get().getClass());
	}

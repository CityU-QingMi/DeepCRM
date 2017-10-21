	@Test
	void beforeAndAfterTestExecutionCallbacks() {
		LauncherDiscoveryRequest request = request().selectors(selectClass(OuterTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		assertEquals(asList(

			// OuterTestCase
			"beforeEachMethodOuter",
				"fooBeforeTestExecutionCallback",
				"barBeforeTestExecutionCallback",
					"testOuter",
				"barAfterTestExecutionCallback",
				"fooAfterTestExecutionCallback",
			"afterEachMethodOuter",

			// InnerTestCase
			"beforeEachMethodOuter",
				"beforeEachMethodInner",
					"fooBeforeTestExecutionCallback",
					"barBeforeTestExecutionCallback",
						"fizzBeforeTestExecutionCallback",
							"testInner",
						"fizzAfterTestExecutionCallback",
					"barAfterTestExecutionCallback",
					"fooAfterTestExecutionCallback",
				"afterEachMethodInner",
			"afterEachMethodOuter"

		), callSequence, "wrong call sequence");
		// @formatter:on
	}

	@Test
	void beforeEachAndAfterEachCallbacks() {
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
			"fooBeforeEachCallback",
			"barBeforeEachCallback",
				"beforeEachMethod",
					"testOuter",
				"afterEachMethod",
			"barAfterEachCallback",
			"fooAfterEachCallback",

			// InnerTestCase
			"fooBeforeEachCallback",
			"barBeforeEachCallback",
			"fizzBeforeEachCallback",
				"beforeEachMethod",
					"beforeEachInnerMethod",
						"testInner",
					"afterEachInnerMethod",
				"afterEachMethod",
			"fizzAfterEachCallback",
			"barAfterEachCallback",
			"fooAfterEachCallback"

			), callSequence, "wrong call sequence");
		// @formatter:on
	}

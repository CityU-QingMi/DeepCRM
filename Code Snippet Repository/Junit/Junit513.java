	@Test
	void beforeEachAndAfterEachCallbacksDeclaredOnInterfaceAndClass() {
		LauncherDiscoveryRequest request = request().selectors(selectClass(TestInterfaceTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		assertEquals(asList(

			// Test Interface
			"fooBeforeEachCallback",
			"barBeforeEachCallback",
				"defaultTestMethod",
			"barAfterEachCallback",
			"fooAfterEachCallback",

			// Test Class
			"fooBeforeEachCallback",
			"barBeforeEachCallback",
				"localTestMethod",
			"barAfterEachCallback",
			"fooAfterEachCallback"

		), callSequence, "wrong call sequence");
		// @formatter:on
	}

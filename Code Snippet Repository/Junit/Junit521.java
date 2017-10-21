	@Test
	void beforeAndAfterTestExecutionCallbacksDeclaredOnInterfaceAndClass() {
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
			"fooBeforeTestExecutionCallback",
				"barBeforeTestExecutionCallback",
					"defaultTestMethod",
				"barAfterTestExecutionCallback",
			"fooAfterTestExecutionCallback",

			// Test Class
			"fooBeforeTestExecutionCallback",
				"barBeforeTestExecutionCallback",
					"localTestMethod",
				"barAfterTestExecutionCallback",
			"fooAfterTestExecutionCallback"

		), callSequence, "wrong call sequence");
		// @formatter:on
	}

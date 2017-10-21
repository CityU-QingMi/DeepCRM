	@Test
	void executeTestWithExceptionThrownInAfterMethod() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(LocalTestCase.class, "throwExceptionInAfterMethod")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(0, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");
	}

	@Test
	void threeReportEntriesArePublished() {
		LauncherDiscoveryRequest request = request().selectors(selectClass(MyReportingTestCase.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(6, eventRecorder.getReportingEntryPublishedCount(), "# report entries published");
	}

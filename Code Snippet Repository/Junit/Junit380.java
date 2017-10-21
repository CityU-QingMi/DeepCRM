	private void assertExecutionResults(Class<?> invalidTestClass) {
		LauncherDiscoveryRequest request = request().selectors(selectClass(TestCase.class),
			selectClass(invalidTestClass)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		// @formatter:off
		assertAll(
			() -> assertEquals(3, eventRecorder.getContainerStartedCount(), "# containers started"),
			() -> assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed"),
			() -> assertEquals(3, eventRecorder.getContainerFinishedCount(), "# containers finished"),
			() -> assertEquals(1, eventRecorder.getContainerFailedCount(), "# containers failed")
		);
		// @formatter:on
	}

	private void assertExecutionConditionOverride(String deactivatePattern, int testStartedCount, int testFailedCount) {
		// @formatter:off
		LauncherDiscoveryRequest request = request()
				.selectors(selectClass(TestCaseWithExecutionConditionOnClass.class))
				.configurationParameter(DEACTIVATE_CONDITIONS_PATTERN_PROPERTY_NAME, deactivatePattern)
				.build();
		// @formatter:on

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(0, eventRecorder.getContainerSkippedCount(), "# containers skipped");
		assertEquals(2, eventRecorder.getContainerStartedCount(), "# containers started");
		assertEquals(testStartedCount, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(testFailedCount, eventRecorder.getTestFailedCount(), "# tests failed");
	}

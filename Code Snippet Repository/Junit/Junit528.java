	private void assertExecutionConditionOverride(String deactivatePattern, int started, int succeeded, int failed) {
		// @formatter:off
		LauncherDiscoveryRequest request = request()
				.selectors(selectClass(TestCaseWithExecutionConditionOnMethods.class))
				.configurationParameter(DEACTIVATE_CONDITIONS_PATTERN_PROPERTY_NAME, deactivatePattern)
				.build();
		// @formatter:on

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(started, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(succeeded, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(failed, eventRecorder.getTestFailedCount(), "# tests failed");
	}

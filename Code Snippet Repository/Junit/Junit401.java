	private void performAssertions(Class<?> testClass, Map<String, String> configParams, int containers,
			int failedContainers, int tests, String... methods) {

		// @formatter:off
		ExecutionEventRecorder eventRecorder = executeTests(
			request()
				.selectors(selectClass(testClass))
				.configurationParameters(configParams)
				.build()
		);

		assertAll(
			() -> assertEquals(containers, eventRecorder.getContainerStartedCount(), "# containers started"),
			() -> assertEquals(containers, eventRecorder.getContainerFinishedCount(), "# containers finished"),
			() -> assertEquals(failedContainers, eventRecorder.getContainerFailedCount(), "# containers failed"),
			() -> assertEquals(tests, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(tests, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(Arrays.asList(methods), methodsInvoked)
		);
		// @formatter:on
	}

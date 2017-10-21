	@Test
	void dynamicTestsAreExecutedFromIterable() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyDynamicTestCase.class, "dynamicIterable")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		// @TestFactory methods are counted as both container and test
		assertAll( //
			() -> assertEquals(3, eventRecorder.getContainerStartedCount(), "# container started"),
			() -> assertEquals(2, eventRecorder.getDynamicTestRegisteredCount(), "# dynamic registered"),
			() -> assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed"),
			() -> assertEquals(3, eventRecorder.getContainerFinishedCount(), "# container finished"));
	}

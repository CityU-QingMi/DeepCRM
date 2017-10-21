	@Test
	void dynamicContainersAreExecutedFromIterable() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyDynamicTestCase.class, "dynamicContainerWithIterable")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(MyDynamicTestCase.class), started()), //
			event(container("dynamicContainerWithIterable"), started()), //
			event(dynamicTestRegistered("dynamic-container:#1")), //
			event(container("dynamic-container:#1"), started()), //
			event(dynamicTestRegistered("dynamic-test:#1")), //
			event(test("dynamic-test:#1", "succeedingTest"), started()), //
			event(test("dynamic-test:#1", "succeedingTest"), finishedSuccessfully()), //
			event(dynamicTestRegistered("dynamic-test:#2")), //
			event(test("dynamic-test:#2", "failingTest"), started()), //
			event(test("dynamic-test:#2", "failingTest"), finishedWithFailure(message("failing"))), //
			event(container("dynamic-container:#1"), finishedSuccessfully()), //
			event(container("dynamicContainerWithIterable"), finishedSuccessfully()), //
			event(container(MyDynamicTestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));

		assertAll( //
			() -> assertEquals(4, eventRecorder.getContainerStartedCount(), "# container started"),
			() -> assertEquals(3, eventRecorder.getDynamicTestRegisteredCount(), "# dynamic tests registered"),
			() -> assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed"),
			() -> assertEquals(4, eventRecorder.getContainerFinishedCount(), "# container finished"));
	}

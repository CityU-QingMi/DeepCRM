	@Test
	void nestedDynamicContainersAreExecuted() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyDynamicTestCase.class, "nestedDynamicContainers")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(MyDynamicTestCase.class), started()), //
			event(container("nestedDynamicContainers"), started()), //
			event(dynamicTestRegistered(displayName("gift wrap"))), //
			event(container(displayName("gift wrap")), started()), //
			event(dynamicTestRegistered(displayName("box"))), //
			event(container(displayName("box")), started()), //
			event(dynamicTestRegistered("dynamic-test:#1")), //
			event(test("dynamic-test:#1", "succeedingTest"), started()), //
			event(test("dynamic-test:#1", "succeedingTest"), finishedSuccessfully()), //
			event(dynamicTestRegistered("dynamic-test:#2")), //
			event(test("dynamic-test:#2", "failingTest"), started()), //
			event(test("dynamic-test:#2", "failingTest"), finishedWithFailure(message("failing"))), //
			event(container(displayName("box")), finishedSuccessfully()), //
			event(container(displayName("gift wrap")), finishedSuccessfully()), //
			event(container("nestedDynamicContainers"), finishedSuccessfully()), //
			event(container(MyDynamicTestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));

		assertAll( //
			() -> assertEquals(5, eventRecorder.getContainerStartedCount(), "# container started"),
			() -> assertEquals(4, eventRecorder.getDynamicTestRegisteredCount(), "# dynamic tests registered"),
			() -> assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed"),
			() -> assertEquals(5, eventRecorder.getContainerFinishedCount(), "# container finished"));
	}

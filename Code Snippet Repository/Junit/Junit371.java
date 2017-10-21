	@Test
	void dynamicContainersChildrenMustNotBeNull() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyDynamicTestCase.class, "dynamicContainerWithNullChildren")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(MyDynamicTestCase.class), started()), //
			event(container("dynamicContainerWithNullChildren"), started()), //
			event(dynamicTestRegistered("dynamic-container:#1")), //
			event(container("dynamic-container:#1"), started()), //
			event(container("dynamic-container:#1"), //
				finishedWithFailure(message("individual dynamic node must not be null"))), //
			event(container("dynamicContainerWithNullChildren"), finishedSuccessfully()), //
			event(container(MyDynamicTestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

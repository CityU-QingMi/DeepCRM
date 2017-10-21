	@Test
	void exceptionHandlerSwallowsException() {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(ATestCase.class, "testSwallow")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertTrue(SwallowException.handleExceptionCalled, "TestExecutionExceptionHandler should have been called");

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(ATestCase.class), started()), //
			event(test("testSwallow"), started()), //
			event(test("testSwallow"), finishedSuccessfully()), //
			event(container(ATestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

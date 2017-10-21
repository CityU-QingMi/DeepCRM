	@Test
	void exceptionHandlerRethrowsException() {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(ATestCase.class, "testRethrow")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertTrue(RethrowException.handleExceptionCalled, "TestExecutionExceptionHandler should have been called");

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(ATestCase.class), started()), //
			event(test("testRethrow"), started()), //
			event(test("testRethrow"), finishedWithFailure(allOf(isA(IOException.class), message("checked")))), //
			event(container(ATestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

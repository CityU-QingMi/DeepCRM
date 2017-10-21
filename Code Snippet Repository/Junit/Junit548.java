	@Test
	void exceptionHandlerConvertsException() {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(ATestCase.class, "testConvert")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertTrue(ConvertException.handleExceptionCalled, "TestExecutionExceptionHandler should have been called");

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(ATestCase.class), started()), //
			event(test("testConvert"), started()), //
			event(test("testConvert"), finishedWithFailure(allOf(isA(IOException.class), message("checked")))), //
			event(container(ATestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

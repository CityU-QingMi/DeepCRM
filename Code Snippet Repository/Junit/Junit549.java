	@Test
	void severalHandlersAreCalledInOrder() {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(ATestCase.class, "testSeveral")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertTrue(ConvertException.handleExceptionCalled, "ConvertException should have been called");
		assertTrue(RethrowException.handleExceptionCalled, "RethrowException should have been called");
		assertTrue(SwallowException.handleExceptionCalled, "SwallowException should have been called");
		assertFalse(ShouldNotBeCalled.handleExceptionCalled, "ShouldNotBeCalled should not have been called");

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			event(engine(), started()), //
			event(container(ATestCase.class), started()), //
			event(test("testSeveral"), started()), //
			event(test("testSeveral"), finishedSuccessfully()), //
			event(container(ATestCase.class), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));

		assertEquals(Arrays.asList("convert", "rethrow", "swallow"), handlerCalls);
	}

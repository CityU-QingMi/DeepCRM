	@Test
	void executesMalformedJUnit4TestCase() {
		Class<?> testClass = MalformedJUnit4TestCase.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(test("initializationError"), started()), //
			event(test("initializationError"), finishedWithFailure(message("Method nonPublicTest() should be public"))), //
			event(container(testClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

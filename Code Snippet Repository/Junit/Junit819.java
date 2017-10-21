	@Test
	void executesParameterizedTestCase() {
		Class<?> testClass = ParameterizedTestCase.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(container("[foo]"), started()), //
			event(test("test[foo]"), started()), //
			event(test("test[foo]"), finishedSuccessfully()), //
			event(container("[foo]"), finishedSuccessfully()), //
			event(container("[bar]"), started()), //
			event(test("test[bar]"), started()), //
			event(test("test[bar]"),
				finishedWithFailure(allOf(isA(AssertionError.class), message("expected:<[foo]> but was:<[bar]>")))), //
			event(container("[bar]"), finishedSuccessfully()), //
			event(container(testClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

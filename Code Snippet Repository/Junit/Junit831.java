	@Test
	void executesJUnit4TestCaseWithErrorInBeforeClass() {
		Class<?> testClass = JUnit4TestCaseWithErrorInBeforeClass.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(container(testClass),
				finishedWithFailure(allOf(isA(AssertionError.class), message("something went wrong")))), //
			event(engine(), finishedSuccessfully()));
	}

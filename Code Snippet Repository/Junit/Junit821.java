	@Test
	void executesJUnit4SuiteWithExceptionThrowingRunner() {
		Class<?> testClass = JUnit4SuiteWithExceptionThrowingRunner.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(container(testClass), finishedWithFailure()), //
			event(engine(), finishedSuccessfully()));
	}

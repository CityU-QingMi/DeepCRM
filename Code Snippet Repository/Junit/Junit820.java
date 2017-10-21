	@Test
	void executesJUnit4TestCaseWithExceptionThrowingRunner() {
		Class<?> testClass = JUnit4TestCaseWithExceptionThrowingRunner.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(test(testClass.getName()), started()), //
			event(test(testClass.getName()), finishedWithFailure()), //
			event(engine(), finishedSuccessfully()));
	}

	@Test
	void executesJUnit4SuiteWithJUnit4TestCaseWithErrorInBeforeClass() {
		Class<?> suiteClass = JUnit4SuiteWithJUnit4TestCaseWithErrorInBeforeClass.class;
		Class<?> testClass = JUnit4TestCaseWithErrorInBeforeClass.class;

		List<ExecutionEvent> executionEvents = execute(suiteClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(suiteClass), started()), //
			event(container(testClass), started()), //
			event(container(testClass),
				finishedWithFailure(allOf(isA(AssertionError.class), message("something went wrong")))), //
			event(container(suiteClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

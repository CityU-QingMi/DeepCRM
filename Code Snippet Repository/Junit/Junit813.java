	@Test
	void executesJUnit4SuiteOfSuiteWithJUnit4TestCaseWithAssumptionFailureInBeforeClass() {
		Class<?> suiteOfSuiteClass = JUnit4SuiteOfSuiteWithJUnit4TestCaseWithAssumptionFailureInBeforeClass.class;
		Class<?> suiteClass = JUnit4SuiteWithJUnit4TestCaseWithAssumptionFailureInBeforeClass.class;
		Class<?> testClass = JUnit4TestCaseWithAssumptionFailureInBeforeClass.class;

		List<ExecutionEvent> executionEvents = execute(suiteOfSuiteClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(suiteOfSuiteClass), started()), //
			event(container(suiteClass), started()), //
			event(container(testClass), started()), //
			event(container(testClass),
				abortedWithReason(allOf(isA(AssumptionViolatedException.class), message("assumption violated")))), //
			event(container(suiteClass), finishedSuccessfully()), //
			event(container(suiteOfSuiteClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

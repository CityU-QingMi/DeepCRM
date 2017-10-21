	@Test
	void executesJUnit4SuiteOfSuiteWithIgnoredJUnit4TestCase() {
		Class<?> suiteOfSuiteClass = JUnit4SuiteOfSuiteWithIgnoredJUnit4TestCase.class;
		Class<?> suiteClass = JUnit4SuiteWithIgnoredJUnit4TestCase.class;
		Class<?> testClass = IgnoredJUnit4TestCase.class;

		List<ExecutionEvent> executionEvents = execute(suiteOfSuiteClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(suiteOfSuiteClass), started()), //
			event(container(suiteClass), started()), //
			event(test(testClass.getName()), skippedWithReason("complete class is ignored")), //
			event(container(suiteClass), finishedSuccessfully()), //
			event(container(suiteOfSuiteClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

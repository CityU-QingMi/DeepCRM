	@Test
	void executesJUnit4SuiteWithPlainJUnit4TestCaseWithSingleTestWhichIsIgnored() {
		Class<?> suiteClass = JUnit4SuiteWithPlainJUnit4TestCaseWithSingleTestWhichIsIgnored.class;
		Class<?> testClass = PlainJUnit4TestCaseWithSingleTestWhichIsIgnored.class;

		List<ExecutionEvent> executionEvents = execute(suiteClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(suiteClass), started()), //
			event(container(testClass), started()), //
			event(test("ignoredTest"), skippedWithReason("ignored test")), //
			event(container(testClass), finishedSuccessfully()), //
			event(container(suiteClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

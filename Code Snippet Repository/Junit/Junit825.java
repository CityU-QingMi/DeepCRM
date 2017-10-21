	@Test
	void executesJUnit4TestCaseWithRunnerWithCustomUniqueIds() {
		Class<?> testClass = JUnit4TestCaseWithRunnerWithCustomUniqueIds.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(uniqueIdSubstring(testClass.getName()), started()), //
			event(uniqueIdSubstring(testClass.getName()), finishedWithFailure()), //
			event(container(testClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

	@Test
	void executesIgnoredJUnit4TestCase() {
		Class<?> testClass = IgnoredJUnit4TestCase.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(test(testClass.getName()), skippedWithReason("complete class is ignored")), //
			event(engine(), finishedSuccessfully()));
	}

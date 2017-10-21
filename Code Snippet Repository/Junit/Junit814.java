	@Test
	void executesJUnit4TestCaseWithErrorInAfterClass() {
		Class<?> testClass = JUnit4TestCaseWithErrorInAfterClass.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(test("failingTest"), started()), //
			event(test("failingTest"),
				finishedWithFailure(allOf(isA(AssertionError.class), message("expected to fail")))), //
			event(test("succeedingTest"), started()), //
			event(test("succeedingTest"), finishedSuccessfully()), //
			event(container(testClass),
				finishedWithFailure(allOf(isA(AssertionError.class), message("error in @AfterClass")))), //
			event(engine(), finishedSuccessfully()));
	}

	@Test
	void executesJUnit4TestCaseWithOverloadedMethod() {
		Class<?> testClass = JUnit4TestCaseWithOverloadedMethod.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(test("theory(" + JUnit4TestCaseWithOverloadedMethod.class.getName() + ")[0]"), started()), //
			event(test("theory(" + JUnit4TestCaseWithOverloadedMethod.class.getName() + ")[0]"), finishedWithFailure()), //
			event(test("theory(" + JUnit4TestCaseWithOverloadedMethod.class.getName() + ")[1]"), started()), //
			event(test("theory(" + JUnit4TestCaseWithOverloadedMethod.class.getName() + ")[1]"), finishedWithFailure()), //
			event(container(testClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

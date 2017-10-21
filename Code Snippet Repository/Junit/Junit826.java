	@Test
	void executesJUnit4TestCaseWithErrorCollectorStoringMultipleFailures() {
		Class<?> testClass = JUnit4TestCaseWithErrorCollectorStoringMultipleFailures.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(test("example"), started()), //
			event(test("example"),
				finishedWithFailure(allOf(isA(MultipleFailuresError.class), //
					new Condition<>(throwable -> ((MultipleFailuresError) throwable).getFailures().size() == 3,
						"Must contain multiple errors (3)")))), //
			event(container(testClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

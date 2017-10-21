	@Test
	void executesPlainJUnit4TestCaseWithFiveTests() {
		Class<?> testClass = PlainJUnit4TestCaseWithFiveTestMethods.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(container(testClass), started()), //
			event(test("abortedTest"), started()), //
			event(test("abortedTest"),
				abortedWithReason(
					allOf(isA(AssumptionViolatedException.class), message("this test should be aborted")))), //
			event(test("failingTest"), started()), //
			event(test("failingTest"),
				finishedWithFailure(allOf(isA(AssertionError.class), message("this test should fail")))), //
			event(test("ignoredTest1_withoutReason"), skippedWithReason("")), //
			event(test("ignoredTest2_withReason"), skippedWithReason("a custom reason")), //
			event(test("successfulTest"), started()), //
			event(test("successfulTest"), finishedSuccessfully()), //
			event(container(testClass), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

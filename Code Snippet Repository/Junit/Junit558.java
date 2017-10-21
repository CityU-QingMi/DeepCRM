	@Test
	void expectedExceptionSupportWithoutExpectedExceptionRule() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(
			ExpectedExceptionSupportWithoutExpectedExceptionRuleTestCase.class);

		assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		assertThat(eventRecorder.getSuccessfulTestFinishedEvents()).have(
			event(test("success"), finishedSuccessfully()));

		assertThat(eventRecorder.getFailedTestFinishedEvents())//
				.haveExactly(1, event(test("failure"), //
					finishedWithFailure(message("must fail"))));
	}

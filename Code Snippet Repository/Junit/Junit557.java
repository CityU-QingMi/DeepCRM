	@Test
	void expectedExceptionIsProcessedCorrectly() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(ExpectedExceptionTestCase.class);

		assertEquals(4, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(3, eventRecorder.getTestFailedCount(), "# tests failed");

		assertThat(eventRecorder.getSuccessfulTestFinishedEvents()).have(
			event(test("correctExceptionExpectedThrown"), finishedSuccessfully()));

		assertThat(eventRecorder.getFailedTestFinishedEvents())//
				.haveExactly(1,
					event(test("noExceptionExpectedButThrown"), //
						finishedWithFailure(message("no exception expected")))) //
				.haveExactly(1,
					event(test("exceptionExpectedButNotThrown"), //
						finishedWithFailure(allOf(isA(AssertionError.class), //
							message("Expected test to throw an instance of java.lang.RuntimeException"))))) //
				.haveExactly(1,
					event(test("wrongExceptionExpected"), //
						finishedWithFailure(allOf(isA(AssertionError.class), //
							message(value -> value.contains("Expected: an instance of java.io.IOException"))))));
	}

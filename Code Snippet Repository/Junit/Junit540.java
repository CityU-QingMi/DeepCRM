	@Test
	void executeTestsForPotentiallyIncompatibleTypeMethodInjectionCases() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(
			PotentiallyIncompatibleTypeMethodInjectionTestCase.class);

		assertEquals(3, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		Predicate<String> expectations = s ->
				s.contains("NumberParameterResolver") &&
				s.contains("resolved a value of type [java.lang.Integer]") &&
				s.contains("but a value assignment compatible with [java.lang.Double] is required");

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getFailedTestFinishedEvents(),
			event(
				test("doubleParameterInjection"),
				finishedWithFailure(allOf(isA(ParameterResolutionException.class), message(expectations)
			))));
		// @formatter:on
	}

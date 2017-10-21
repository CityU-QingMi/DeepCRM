	@Test
	void executeTestsForNullValuedMethodInjectionCases() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(NullMethodInjectionTestCase.class);

		assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		// @formatter:off
		Predicate<String> expectations = s ->
				s.contains("NullIntegerParameterResolver") &&
				s.contains("resolved a null value for parameter") &&
				s.contains("but a primitive of type [int] is required");

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getFailedTestFinishedEvents(),
			event(
				test("injectPrimitive"),
				finishedWithFailure(allOf(isA(ParameterResolutionException.class), message(expectations)))
			));
		// @formatter:on
	}

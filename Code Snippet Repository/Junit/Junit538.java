	@Test
	void executeTestsForMethodInjectionCases() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(MethodInjectionTestCase.class);

		assertEquals(7, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(6, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");
	}

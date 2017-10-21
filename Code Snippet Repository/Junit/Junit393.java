	@Test
	void executeAllTestsInClass() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(LocalTestCase.class);

		assertEquals(6, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(3, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(1, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(2, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(6, LocalTestCase.countBeforeInvoked, "# before calls");
		assertEquals(6, LocalTestCase.countAfterInvoked, "# after calls");
		assertEquals(6, AbstractTestCase.countSuperBeforeInvoked, "# super before calls");
		assertEquals(6, AbstractTestCase.countSuperAfterInvoked, "# super after calls");
	}

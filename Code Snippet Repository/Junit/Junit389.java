	@Test
	void allTestsInClassAreRunWithBeforeEach() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(MyStandardTestCase.class);

		assertEquals(4, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(1, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(2, eventRecorder.getContainerStartedCount(), "# containers started");
		assertEquals(2, eventRecorder.getContainerFinishedCount(), "# containers finished");

		assertEquals(4, MyStandardTestCase.countBefore1, "# before1 calls");
		assertEquals(4, MyStandardTestCase.countBefore2, "# before2 calls");
	}

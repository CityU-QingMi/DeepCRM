	@Test
	void testsFailWhenAfterEachFails() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(TestCaseWithFailingAfter.class);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(0, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(2, eventRecorder.getContainerStartedCount(), "# containers started");
		assertEquals(2, eventRecorder.getContainerFinishedCount(), "# containers finished");

		assertTrue(TestCaseWithFailingAfter.testExecuted, "test executed?");
	}

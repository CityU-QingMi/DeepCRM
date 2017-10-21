	@Test
	void verifierSupportForErrorCollectorFieldFailsTheTest() {
		ExecutionEventRecorder eventRecorder = executeTestsForClass(VerifierSupportForErrorCollectorTestCase.class);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(0, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(1, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(true, VerifierSupportForErrorCollectorTestCase.survivedBothErrors, "after of rule 1 executed?");
	}

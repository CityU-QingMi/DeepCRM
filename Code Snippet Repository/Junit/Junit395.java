	@Test
	void executeTestDeclaredInSuperClass() {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(LocalTestCase.class, "superTest")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(0, eventRecorder.getTestSkippedCount(), "# tests skipped");
		assertEquals(0, eventRecorder.getTestAbortedCount(), "# tests aborted");
		assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed");

		assertEquals(1, LocalTestCase.countBeforeInvoked, "# after calls");
		assertEquals(1, LocalTestCase.countAfterInvoked, "# after calls");
		assertEquals(1, AbstractTestCase.countSuperBeforeInvoked, "# super before calls");
		assertEquals(1, AbstractTestCase.countSuperAfterInvoked, "# super after calls");

	}

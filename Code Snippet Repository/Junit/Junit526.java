	@Test
	void conditionWorksOnTest() {
		LauncherDiscoveryRequest request = request().selectors(
			selectClass(TestCaseWithExecutionConditionOnMethods.class)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(3, eventRecorder.getTestSkippedCount(), "# tests skipped");
	}

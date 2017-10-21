	@Test
	void executeTestsWithDisabledTestMethods() throws Exception {
		LauncherDiscoveryRequest request = request().selectors(selectClass(DisabledTestMethodsTestCase.class)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");
		assertEquals(1, eventRecorder.getTestSkippedCount(), "# tests skipped");

		String method = DisabledTestMethodsTestCase.class.getDeclaredMethod("disabledTest").toString();
		String reason = eventRecorder.getSkippedTestEvents().get(0).getPayload(String.class).get();
		assertEquals(method + " is @Disabled", reason);
	}

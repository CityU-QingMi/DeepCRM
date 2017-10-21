	@Test
	void executeTestCaseWithDefaultMethodFromInterfaceSelectedByFullyQualifedMethodName() {
		String fqmn = TestCaseWithDefaultMethod.class.getName() + "#test";
		LauncherDiscoveryRequest request = request().selectors(selectMethod(fqmn)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		// @formatter:off
		assertAll(
				() -> assertTrue(beforeAllInvoked, "@BeforeAll static method invoked from interface"),
				() -> assertTrue(afterAllInvoked, "@AfterAll static method invoked from interface"),
				() -> assertTrue(defaultMethodInvoked, "default @Test method invoked from interface"),
				() -> assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started"),
				() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
				() -> assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed")
		);
		// @formatter:on
	}

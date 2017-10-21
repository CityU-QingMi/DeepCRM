	@Test
	void executeTestCaseWithOverloadedMethodNextToGenericDefaultMethodSelectedByClass() throws Exception {
		Class<?> clazz = GenericTestCaseWithDefaultMethod.class;
		LauncherDiscoveryRequest request = request().selectors(selectClass(clazz)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		// @formatter:off
		assertAll(
				() -> assertTrue(beforeAllInvoked, "@BeforeAll default method invoked from interface"),
				() -> assertTrue(afterAllInvoked, "@AfterAll default method invoked from interface"),
				() -> assertTrue(defaultMethodInvoked, "default @Test method invoked from interface"),
				() -> assertTrue(localMethodInvoked, "local @Test method invoked from class"),
				() -> assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started"),
				() -> assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
				() -> assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed")
		);
		// @formatter:on
	}

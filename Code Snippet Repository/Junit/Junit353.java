	@Test
	void executeTestCaseWithDefaultMethodFromGenericInterfaceSelectedByFullyQualifedMethodName() throws Exception {
		String fqmn = GenericTestCaseWithDefaultMethod.class.getName() + "#test(" + Long.class.getName() + ")";
		LauncherDiscoveryRequest request = request().selectors(selectMethod(fqmn)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		// @formatter:off
		assertAll(
				() -> assertTrue(beforeAllInvoked, "@BeforeAll default method invoked from interface"),
				() -> assertTrue(afterAllInvoked, "@AfterAll default method invoked from interface"),
				() -> assertTrue(defaultMethodInvoked, "default @Test method invoked from interface"),
				() -> assertFalse(localMethodInvoked, "local @Test method should not have been invoked from class"),
				() -> assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started"),
				() -> assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
				() -> assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed")
		);
		// @formatter:on
	}

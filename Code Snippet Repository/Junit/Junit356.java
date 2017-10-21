	@Test
	void executeTestCaseWithOverriddenGenericDefaultMethodSelectedByClass() throws Exception {
		Class<?> clazz = GenericTestCaseWithOverriddenDefaultMethod.class;
		LauncherDiscoveryRequest request = request().selectors(selectClass(clazz)).build();
		ExecutionEventRecorder eventRecorder = executeTests(request);

		// @formatter:off
		assertAll(
				() -> assertTrue(beforeAllInvoked, "@BeforeAll default method invoked from interface"),
				() -> assertTrue(afterAllInvoked, "@AfterAll default method invoked from interface"),
				() -> assertFalse(defaultMethodInvoked, "default @Test method should not have been invoked from interface"),
				() -> assertTrue(overriddenDefaultMethodInvoked, "overridden default @Test method invoked from interface"),
				() -> assertTrue(localMethodInvoked, "local @Test method invoked from class"),
				// If defaultMethodInvoked is false and the following ends up being
				// 3 instead of 2, that means that the overriding method gets invoked
				// twice: once as itself and a second time "as" the default method which
				// should not have been "discovered" since it is overridden.
				() -> assertEquals(2, eventRecorder.getTestStartedCount(), "# tests started"),
				() -> assertEquals(2, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
				() -> assertEquals(0, eventRecorder.getTestFailedCount(), "# tests failed")
		);
		// @formatter:on
	}

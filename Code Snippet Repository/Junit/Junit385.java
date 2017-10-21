	@Test
	void executeTestCaseWithOverloadedMethodsAndThenRerunOnlyOneOfTheMethodsSelectedByUniqueId() {
		LauncherDiscoveryRequest request = request().selectors(selectClass(TestCase.class)).build();
		ExecutionEventRecorder eventRecorder1 = executeTests(request);

		// @formatter:off
		assertAll(
				() -> assertEquals(2, eventRecorder1.getTestStartedCount(), "# tests started"),
				() -> assertEquals(2, eventRecorder1.getTestSuccessfulCount(), "# tests succeeded"),
				() -> assertEquals(0, eventRecorder1.getTestFailedCount(), "# tests failed"));
		// @formatter:on

		Optional<ExecutionEvent> first = eventRecorder1.getSuccessfulTestFinishedEvents().stream().filter(
			event -> event.getTestDescriptor().getUniqueId().toString().contains(TestInfo.class.getName())).findFirst();
		assertTrue(first.isPresent());
		TestIdentifier testIdentifier = TestIdentifier.from(first.get().getTestDescriptor());
		String uniqueId = testIdentifier.getUniqueId();

		request = request().selectors(selectUniqueId(uniqueId)).build();
		ExecutionEventRecorder eventRecorder2 = executeTests(request);

		// @formatter:off
		assertAll(
				() -> assertEquals(1, eventRecorder2.getTestStartedCount(), "# tests started"),
				() -> assertEquals(1, eventRecorder2.getTestSuccessfulCount(), "# tests succeeded"),
				() -> assertEquals(0, eventRecorder2.getTestFailedCount(), "# tests failed"));
		// @formatter:on

		first = eventRecorder2.getSuccessfulTestFinishedEvents().stream().filter(
			event -> event.getTestDescriptor().getUniqueId().toString().contains(TestInfo.class.getName())).findFirst();
		assertTrue(first.isPresent());
	}

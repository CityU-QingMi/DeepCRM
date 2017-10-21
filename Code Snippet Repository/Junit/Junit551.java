	@Test
	void testSpecificTestInstancePostProcessorIsCalled() {
		LauncherDiscoveryRequest request = request().selectors(
			selectClass(TestCaseWithTestSpecificTestInstancePostProcessor.class)).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertEquals(1, eventRecorder.getTestStartedCount(), "# tests started");
		assertEquals(1, eventRecorder.getTestSuccessfulCount(), "# tests succeeded");

		assertThat(callSequence).containsExactly(
			"fooPostProcessTestInstance:TestCaseWithTestSpecificTestInstancePostProcessor", "beforeEachMethod", "test");
	}

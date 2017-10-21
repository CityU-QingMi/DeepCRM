	private void performAssertions(Class<?> testClass, int containers, int tests, int instances, int nestedInstances,
			int allMethods, int eachMethods) {

		ExecutionEventRecorder eventRecorder = executeTestsForClass(testClass);

		// @formatter:off
		assertAll(
			() -> assertEquals(containers, eventRecorder.getContainerStartedCount(), "# containers started"),
			() -> assertEquals(containers, eventRecorder.getContainerFinishedCount(), "# containers finished"),
			() -> assertEquals(tests, eventRecorder.getTestStartedCount(), "# tests started"),
			() -> assertEquals(tests, eventRecorder.getTestSuccessfulCount(), "# tests succeeded"),
			() -> assertEquals(instances, instanceCount, "instance count"),
			() -> assertEquals(nestedInstances, nestedInstanceCount, "nested instance count"),
			() -> assertEquals(allMethods, beforeAllCount, "@BeforeAll count"),
			() -> assertEquals(allMethods, afterAllCount, "@AfterAll count"),
			() -> assertEquals(eachMethods, beforeEachCount, "@BeforeEach count"),
			() -> assertEquals(eachMethods, afterEachCount, "@AfterEach count")
		);
		// @formatter:on
	}

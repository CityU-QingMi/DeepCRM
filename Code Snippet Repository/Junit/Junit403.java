	@Test
	void instancePerClassCanBeUsedForKotlinTestClasses() {
		Class<?> testClass = InstancePerClassKotlinTestCase.class;
		InstancePerClassKotlinTestCase.TEST_INSTANCES.clear();

		ExecutionEventRecorder eventRecorder = executeTestsForClass(testClass);

		assertThat(eventRecorder.getTestFinishedCount()).isEqualTo(2);
		assertThat(InstancePerClassKotlinTestCase.TEST_INSTANCES.keySet()).hasSize(1);
		assertThat(getOnlyElement(InstancePerClassKotlinTestCase.TEST_INSTANCES.values())) //
				.containsEntry("beforeAll", 1) //
				.containsEntry("beforeEach", 2) //
				.containsEntry("test", 2) //
				.containsEntry("afterEach", 2) //
				.containsEntry("afterAll", 1);
	}

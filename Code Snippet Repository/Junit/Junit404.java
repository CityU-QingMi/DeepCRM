	@Test
	void instancePerMethodIsDefaultForKotlinTestClasses() {
		Class<?> testClass = InstancePerMethodKotlinTestCase.class;
		InstancePerMethodKotlinTestCase.TEST_INSTANCES.clear();

		ExecutionEventRecorder eventRecorder = executeTestsForClass(testClass);

		assertThat(eventRecorder.getTestFinishedCount()).isEqualTo(2);
		List<Object> instances = new ArrayList<>(InstancePerMethodKotlinTestCase.TEST_INSTANCES.keySet());
		assertThat(instances) //
				.hasSize(3) //
				.extracting(o -> (Object) o.getClass()) //
				.containsExactly(InstancePerMethodKotlinTestCase.Companion.getClass(), //
					InstancePerMethodKotlinTestCase.class, //
					InstancePerMethodKotlinTestCase.class);
		assertThat(InstancePerMethodKotlinTestCase.TEST_INSTANCES.get(instances.get(0))) //
				.containsEntry("beforeAll", 1) //
				.containsEntry("afterAll", 1);
		assertThat(InstancePerMethodKotlinTestCase.TEST_INSTANCES.get(instances.get(1))) //
				.containsEntry("beforeEach", 1) //
				.containsEntry("test", 1) //
				.containsEntry("afterEach", 1);
		assertThat(InstancePerMethodKotlinTestCase.TEST_INSTANCES.get(instances.get(2))) //
				.containsEntry("beforeEach", 1) //
				.containsEntry("test", 1) //
				.containsEntry("afterEach", 1);
	}

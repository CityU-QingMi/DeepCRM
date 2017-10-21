	@Test
	void reportsDynamicTestsForUnknownDescriptions() {
		Class<?> testClass = DynamicTestClass.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(test(testClass.getName()), started()), //
			event(dynamicTestRegistered("dynamicTest")), //
			event(test("dynamicTest"), started()), //
			event(test("dynamicTest"), finishedSuccessfully()), //
			event(test(testClass.getName()), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

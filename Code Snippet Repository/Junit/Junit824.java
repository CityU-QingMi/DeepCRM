	@Test
	void ignoreEventsForUnknownDescriptionsByMisbehavingChildlessRunner() {
		Class<?> testClass = MisbehavingChildTestClass.class;

		List<ExecutionEvent> executionEvents = execute(testClass);

		assertRecordedExecutionEventsContainsExactly(executionEvents, //
			event(engine(), started()), //
			event(test(testClass.getName()), started()), //
			event(dynamicTestRegistered("doesNotExist")), //
			event(test("doesNotExist"), started()), //
			event(test(testClass.getName()), finishedSuccessfully()), //
			event(engine(), finishedSuccessfully()));
	}

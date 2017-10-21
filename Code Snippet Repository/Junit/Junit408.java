	@Test
	void templateWithDynamicTestInstancePostProcessorIsInvoked() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithDynamicTestInstancePostProcessor")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithDynamicTestInstancePostProcessor"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1")), //
				event(test("test-template-invocation:#1"), started()), //
				event(test("test-template-invocation:#1"), finishedWithFailure(message("foo"))), //
				event(dynamicTestRegistered("test-template-invocation:#2")), //
				event(test("test-template-invocation:#2"), started()), //
				event(test("test-template-invocation:#2"), finishedWithFailure(message("bar"))), //
				event(container("templateWithDynamicTestInstancePostProcessor"), finishedSuccessfully())));
	}

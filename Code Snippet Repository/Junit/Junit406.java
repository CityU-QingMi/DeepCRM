	@Test
	void templateWithSingleRegisteredExtensionIsInvoked() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithSingleRegisteredExtension")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithSingleRegisteredExtension"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1")), //
				event(test("test-template-invocation:#1"), started()), //
				event(test("test-template-invocation:#1"),
					finishedWithFailure(message("invocation is expected to fail"))), //
				event(container("templateWithSingleRegisteredExtension"), finishedSuccessfully())));
	}

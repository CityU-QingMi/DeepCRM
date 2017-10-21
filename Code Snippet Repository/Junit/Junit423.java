	@Test
	void templateWithTwoInvocationsFromSingleExtensionIsInvoked() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithTwoInvocationsFromSingleExtension")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithTwoInvocationsFromSingleExtension"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1"), displayName("[1]")), //
				event(test("test-template-invocation:#1"), started()), //
				event(test("test-template-invocation:#1"),
					finishedWithFailure(message("invocation is expected to fail"))), //
				event(dynamicTestRegistered("test-template-invocation:#2"), displayName("[2]")), //
				event(test("test-template-invocation:#2"), started()), //
				event(test("test-template-invocation:#2"),
					finishedWithFailure(message("invocation is expected to fail"))), //
				event(container("templateWithTwoInvocationsFromSingleExtension"), finishedSuccessfully())));
	}

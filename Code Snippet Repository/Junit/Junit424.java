	@Test
	void templateWithDisabledInvocationsIsSkipped() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithDisabledInvocations")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithDisabledInvocations"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1")), //
				event(test("test-template-invocation:#1"), skippedWithReason("always disabled")), //
				event(container("templateWithDisabledInvocations"), finishedSuccessfully())));
	}

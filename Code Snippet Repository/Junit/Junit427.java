	@Test
	void templateWithDynamicParameterResolverIsInvoked() {
		LauncherDiscoveryRequest request = request().selectors(selectMethod(MyTestTemplateTestCase.class,
			"templateWithDynamicParameterResolver", "java.lang.String")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithDynamicParameterResolver"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1"), displayName("[1] foo")), //
				event(test("test-template-invocation:#1"), started()), //
				event(test("test-template-invocation:#1"), finishedWithFailure(message("foo"))), //
				event(dynamicTestRegistered("test-template-invocation:#2"), displayName("[2] bar")), //
				event(test("test-template-invocation:#2"), started()), //
				event(test("test-template-invocation:#2"), finishedWithFailure(message("bar"))), //
				event(container("templateWithDynamicParameterResolver"), finishedSuccessfully())));
	}

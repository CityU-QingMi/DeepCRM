	@Test
	void contextParameterResolverCanResolveConstructorArguments() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCaseWithConstructor.class, "template", "java.lang.String")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("template"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1"), displayName("[1] foo")), //
				event(test("test-template-invocation:#1"), started()), //
				event(test("test-template-invocation:#1"), finishedSuccessfully()), //
				event(dynamicTestRegistered("test-template-invocation:#2"), displayName("[2] bar")), //
				event(test("test-template-invocation:#2"), started()), //
				event(test("test-template-invocation:#2"), finishedSuccessfully()), //
				event(container("template"), finishedSuccessfully())));
	}

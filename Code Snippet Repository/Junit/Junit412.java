	@Test
	void templateWithCloseableStream() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithCloseableStream")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertThat(InvocationContextProviderWithCloseableStream.streamClosed.get()).describedAs(
			"streamClosed").isTrue();

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithCloseableStream"), started()), //
				event(dynamicTestRegistered("test-template-invocation:#1")), //
				event(test("test-template-invocation:#1"), started()), //
				event(test("test-template-invocation:#1"), finishedSuccessfully()), //
				event(container("templateWithCloseableStream"), finishedSuccessfully())));
	}

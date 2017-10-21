	@Test
	void templateWithSupportingProviderButNoInvocationsReportsFailure() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithSupportingProviderButNoInvocations")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithSupportingProviderButNoInvocations"), started()), //
				event(container("templateWithSupportingProviderButNoInvocations"), finishedWithFailure(
					message("No supporting TestTemplateInvocationContextProvider provided an invocation context")))));
	}

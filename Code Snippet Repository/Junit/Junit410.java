	@Test
	void extensionIsAskedForSupportBeforeItMustProvide() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithWrongParameterType", int.class.getName())).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		assertRecordedExecutionEventsContainsExactly(eventRecorder.getExecutionEvents(), //
			wrappedInContainerEvents(MyTestTemplateTestCase.class, //
				event(container("templateWithWrongParameterType"), started()), //
				event(container("templateWithWrongParameterType"), finishedWithFailure(message(s -> s.startsWith(
					"You must register at least one TestTemplateInvocationContextProvider that supports @TestTemplate method ["))))));
	}

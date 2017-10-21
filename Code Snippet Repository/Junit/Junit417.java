	@Test
	void parentChildRelationshipIsEstablished() {
		LauncherDiscoveryRequest request = request().selectors(
			selectMethod(MyTestTemplateTestCase.class, "templateWithSingleRegisteredExtension")).build();

		ExecutionEventRecorder eventRecorder = executeTests(request);

		TestDescriptor templateMethodDescriptor = findTestDescriptor(eventRecorder,
			container("templateWithSingleRegisteredExtension"));
		TestDescriptor invocationDescriptor = findTestDescriptor(eventRecorder, test("test-template-invocation:#1"));
		assertThat(invocationDescriptor.getParent()).hasValue(templateMethodDescriptor);
		assertThat(templateMethodDescriptor.getChildren()).isEqualTo(singleton(invocationDescriptor));
	}

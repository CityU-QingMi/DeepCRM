	@Test
	void streamsReturnedByProvidersAreClosedWhenCallingProvide() {
		ExtensionContext extensionContext = getExtensionContextReturningSingleMethod(
			new TestCaseWithArgumentSourceAnnotatedMethod());

		Stream<TestTemplateInvocationContext> stream = this.parameterizedTestExtension.provideTestTemplateInvocationContexts(
			extensionContext);

		//cause the stream to be evaluated
		stream.count();
		assertTrue(streamWasClosed);
	}

	@Test
	void throwsExceptionWhenParameterizedTestIsNotInvokedAtLeastOnce() {
		ExtensionContext extensionContextWithAnnotatedTestMethod = getExtensionContextReturningSingleMethod(
			new TestCaseWithAnnotatedMethod());

		Stream<TestTemplateInvocationContext> stream = this.parameterizedTestExtension.provideTestTemplateInvocationContexts(
			extensionContextWithAnnotatedTestMethod);
		//cause the stream to be evaluated
		stream.toArray();
		JUnitException exception = assertThrows(JUnitException.class, stream::close);

		assertThat(exception).hasMessage(
			"Configuration error: You must provide at least one argument for this @ParameterizedTest");
	}

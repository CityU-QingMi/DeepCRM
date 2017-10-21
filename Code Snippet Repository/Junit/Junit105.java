	@Override
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		Method testMethod = context.getRequiredTestMethod();
		String displayName = context.getDisplayName();
		RepeatedTest repeatedTest = AnnotationUtils.findAnnotation(testMethod, RepeatedTest.class).get();
		int totalRepetitions = totalRepetitions(repeatedTest, testMethod);
		RepeatedTestDisplayNameFormatter formatter = displayNameFormatter(repeatedTest, testMethod, displayName);

		// @formatter:off
		return IntStream
				.rangeClosed(1, totalRepetitions)
				.mapToObj(repetition -> new RepeatedTestInvocationContext(repetition, totalRepetitions, formatter));
		// @formatter:on
	}

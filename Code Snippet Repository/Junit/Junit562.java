	@Override
	public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
		Method templateMethod = context.getRequiredTestMethod();
		ParameterizedTestNameFormatter formatter = createNameFormatter(templateMethod);
		AtomicLong invocationCount = new AtomicLong(0);
		// @formatter:off
		return findRepeatableAnnotations(templateMethod, ArgumentsSource.class)
				.stream()
				.map(ArgumentsSource::value)
				.map(ReflectionUtils::newInstance)
				.map(provider -> AnnotationConsumerInitializer.initialize(templateMethod, provider))
				.flatMap(provider -> arguments(provider, context))
				.map(Arguments::get)
				.map(arguments -> createInvocationContext(formatter, arguments))
				.peek(invocationContext -> invocationCount.incrementAndGet())
				.onClose(() ->
						Preconditions.condition(invocationCount.get() > 0,
								() -> "Configuration error: You must provide at least one argument for this @" + ParameterizedTest.class.getSimpleName()));
		// @formatter:on
	}

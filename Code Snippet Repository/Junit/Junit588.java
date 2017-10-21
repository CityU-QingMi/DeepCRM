	private Stream<Object[]> provideArguments(Class<?> testClass, boolean allowNonStaticMethod, String... methodNames) {
		MethodSource annotation = mock(MethodSource.class);
		when(annotation.value()).thenReturn(methodNames);

		ExtensionContext context = mock(ExtensionContext.class);
		when(context.getTestClass()).thenReturn(Optional.ofNullable(testClass));
		doCallRealMethod().when(context).getRequiredTestClass();

		Object testInstance = allowNonStaticMethod ? ReflectionUtils.newInstance(testClass) : null;
		when(context.getTestInstance()).thenReturn(Optional.ofNullable(testInstance));

		MethodArgumentsProvider provider = new MethodArgumentsProvider();
		provider.accept(annotation);
		return provider.provideArguments(context).map(Arguments::get);
	}

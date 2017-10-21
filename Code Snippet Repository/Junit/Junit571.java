	@Override
	public Stream<Arguments> provideArguments(ExtensionContext context) {
		Class<?> testClass = context.getRequiredTestClass();
		Object testInstance = context.getTestInstance().orElse(null);
		// @formatter:off
		return Arrays.stream(methodNames)
				.map(methodName -> ReflectionUtils.findMethod(testClass, methodName)
					.orElseThrow(() -> new JUnitException("Could not find method: " + methodName)))
				.map(method -> ReflectionUtils.invokeMethod(method, testInstance))
				.flatMap(CollectionUtils::toStream)
				.map(MethodArgumentsProvider::toArguments);
		// @formatter:on
	}
